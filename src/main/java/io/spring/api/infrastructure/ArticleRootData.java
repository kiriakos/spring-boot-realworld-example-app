package io.spring.api.infrastructure;

import io.spring.application.data.ArticleData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRootData {

	public ArticleData article;
}