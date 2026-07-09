package org.keroshi.keroshiblog.repository;

import org.keroshi.keroshiblog.domain.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteCodeRepository extends JpaRepository<InviteCode, String> {

}
