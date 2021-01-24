package io.spring.api.infrastructure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.spring.application.data.ArticleData;
import io.spring.application.data.CommentData;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create ResponseEntity instances based on business rules
 * 
 * @author kiriakos
 */
public class Response {

	public static ArticleRootData of(ArticleData article) {
		return new ArticleRootData(article);
	}
	
	public static CommentRootData of(CommentData comment) {
		return new CommentRootData(comment);
	}
	
	// Note: type erasure might cause trouble if we need more of this
	public static CommentListData of(List<CommentData> comments) {
		return new CommentListData(comments);
	}
}

