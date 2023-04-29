package com.agile.demo.biz.project.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {

    Optional<AccountProjectEntity> findByUserId(String userId);

    Optional<AccountProjectEntity> findByNpSeq(Long np_seq);

    Optional<AccountProjectEntity> deleteByUserId(String userId);

    Optional<AccountProjectEntity> deleteByNpSeq(Long np_seq);
}
