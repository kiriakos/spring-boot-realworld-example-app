package io.spring.api.infrastructure;

import io.spring.application.data.CommentData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRootData {

	public CommentData comment;
}
