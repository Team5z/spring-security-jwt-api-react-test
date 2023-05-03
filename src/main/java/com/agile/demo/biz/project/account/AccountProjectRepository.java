package com.agile.demo.biz.project.account;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {


    Optional<AccountProjectEntity> findByAccounts_UserId(String userId);

    void deleteByAccounts_UserId(String userId);

// 메소드 오류 문제
    Optional<AccountProjectEntity> findByAccounts_Seq(Long np_seq);
//
//    void deleteByProjects_Np_seq(Long np_Seq);

    // 여기서 오류가 발생하는 이유도 찾기
//    Optional<AccountProjectEntity> findByAccounts_UserId(String account_seq);
//
//    Optional<AccountProjectEntity> findBy_seq(Long np_seq);
//
//    Optional<AccountProjectEntity> deleteByAccounts(String userId);
//
//    Optional<AccountProjectEntity> deleteByProjects(Long np_seq);
}
