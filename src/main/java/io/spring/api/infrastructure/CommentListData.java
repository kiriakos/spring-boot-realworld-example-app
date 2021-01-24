package io.spring.api.infrastructure;

import java.util.List;

import io.spring.application.data.CommentData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentListData {

	public List<CommentData> comments;
}
