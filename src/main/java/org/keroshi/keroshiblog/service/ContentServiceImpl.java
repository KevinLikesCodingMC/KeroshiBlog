package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.Content;
import org.keroshi.keroshiblog.mapper.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {
	private final ContentRepository contentRepository;
	public ContentServiceImpl(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	@Override
	public void saveContent(Content content) {
		contentRepository.save(content);
	}

	@Override
	public Optional<Content> getContentByName(String name) {
		return this.contentRepository.findByName(name);
	}

	@Override
	public void checkContentByName(String name) {
		if(getContentByName(name).isEmpty()) {
			Content content = new Content();
			content.setName(name);
			saveContent(content);
		}
	}
}
