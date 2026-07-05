package org.keroshi.keroshiblog.repository;

import org.keroshi.keroshiblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsernameNormalized(String username);
}
