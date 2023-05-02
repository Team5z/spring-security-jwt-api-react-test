package com.agile.demo.biz.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
//    // 특정 필드의 값을 받아오는 쿼리 - Long으로 Entity로 변환시 오류 발생함
//    @Query(value = "SELECT p.np_seq FROM ProjectEntity p WHERE p.np_seq = :np_seq")
//    Optional<ProjectEntity> findNpSeq(@Param("np_seq") Long npSeq);
}
