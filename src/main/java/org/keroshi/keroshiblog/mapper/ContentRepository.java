package org.keroshi.keroshiblog.mapper;

import org.keroshi.keroshiblog.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
	Optional<Content> findByName(String name);
}
