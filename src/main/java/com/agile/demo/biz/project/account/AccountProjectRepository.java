package com.agile.demo.biz.project.account;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {

    // 여기서 오류가 발생하는 이유도 찾기
//    Optional<AccountProjectEntity> findByAccounts_UserId(String account_seq);
//
//    Optional<AccountProjectEntity> findByProjects_Np_seq(Long np_seq);
//
//    Optional<AccountProjectEntity> deleteByAccounts(String userId);
//
//    Optional<AccountProjectEntity> deleteByProjects(Long np_seq);
}
