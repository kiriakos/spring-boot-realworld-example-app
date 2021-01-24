package io.spring.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.spring.api.exception.NoAuthorizationException;
import io.spring.api.exception.ResourceNotFoundException;
import io.spring.core.service.AuthorizationService;
import io.spring.application.ArticleQueryService;
import io.spring.core.article.ArticleRepository;
import io.spring.core.user.User;
import io.spring.api.infrastructure.ArticleRootData;
import io.spring.api.infrastructure.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/articles/{slug}")
public class ArticleApi {
	
    private ArticleQueryService articleQueryService;
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleApi(ArticleQueryService articleQueryService, ArticleRepository articleRepository) {
    	
        this.articleQueryService = articleQueryService;
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public ArticleRootData article(@PathVariable("slug") String slug,
                                     @AuthenticationPrincipal User user) {
    	
        return articleQueryService.findBySlug(slug, user)
            .map(articleData -> Response.of(articleData))
            .orElseThrow(ResourceNotFoundException::new);
    }

    @PutMapping
    public ArticleRootData updateArticle(@PathVariable("slug") String slug,
                                           @AuthenticationPrincipal User user,
                                           @Valid @RequestBody UpdateArticleParam updateArticleParam) {
    	
        return articleRepository.findBySlug(slug).map(article -> {
        	
            if (!AuthorizationService.canWriteArticle(user, article)) {
                throw new NoAuthorizationException();
            }
            article.update(
                updateArticleParam.getTitle(),
                updateArticleParam.getDescription(),
                updateArticleParam.getBody());
            articleRepository.save(article);
            
            return Response.of(articleQueryService.findBySlug(slug, user).get());
            
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity deleteArticle(@PathVariable("slug") String slug,
                                        @AuthenticationPrincipal User user) {
    	
        return articleRepository.findBySlug(slug).map(article -> {
            if (!AuthorizationService.canWriteArticle(user, article)) {
                throw new NoAuthorizationException();
            }
            articleRepository.remove(article);
            return ResponseEntity.noContent().build();
        }).orElseThrow(ResourceNotFoundException::new);
    }
}

@Getter
@NoArgsConstructor
@JsonRootName("article")
class UpdateArticleParam {
    private String title = "";
    private String body = "";
    private String description = "";
}
