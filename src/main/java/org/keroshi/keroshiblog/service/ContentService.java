package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.Content;

import java.util.Optional;

public interface ContentService {
	void saveContent(Content content);

	Optional<Content> getContentByName(String name);

	void checkContentByName(String name);
}
