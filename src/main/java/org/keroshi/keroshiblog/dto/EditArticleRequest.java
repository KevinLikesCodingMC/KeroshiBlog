package org.keroshi.keroshiblog.dto;

public record EditArticleRequest(
		Long id,
		String name,
		String title,
		String content,
		boolean hidden
) {
}
