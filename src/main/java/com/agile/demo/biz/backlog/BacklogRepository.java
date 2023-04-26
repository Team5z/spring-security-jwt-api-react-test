package com.agile.demo.biz.backlog;

import com.agile.demo.api.sample.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<BacklogEntity, Long> {
}
