package com.agile.demo.biz.backlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BacklogRepository extends JpaRepository<BacklogEntity, Long> {
    Optional<BacklogEntity> findByProject_Seq(Long np_seq);
}
