package com.agile.demo.biz.project.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {
}
