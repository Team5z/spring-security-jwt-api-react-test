package com.agile.demo.biz.accountproject;

import com.agile.demo.api.sample.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {
}
