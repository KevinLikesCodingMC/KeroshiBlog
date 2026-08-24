package org.keroshi.keroshiblog.dto;

import org.keroshi.keroshiblog.domain.Article;

import java.time.Instant;

public record ArticleResponse (
	Long id,
	String name,
	String title,
	String content,
	Long authorId,
	String authorName,
	Instant createTime,
	Instant updateTime,
	boolean hidden
) {
	public static ArticleResponse from(Article article) {

		String title = article.getTitle();
		if (title == null || title.isEmpty()) {
			title = article.getName();
		}

		return new ArticleResponse(
				article.getId(),
				article.getName(),
				title,
				article.getContent(),
				article.getAuthor().getId(),
				article.getAuthor().getUsername(),
				article.getCreateTime(),
				article.getUpdateTime(),
				article.isHidden()
		);
	}
}
