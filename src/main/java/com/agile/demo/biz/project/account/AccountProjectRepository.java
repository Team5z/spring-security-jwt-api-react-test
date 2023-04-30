package com.agile.demo.biz.project.account;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {

    // 여기서 오류가 발생하는 이유도 찾기
    Optional<AccountEntity> findByAccounts(Long account_seq);

    Optional<ProjectEntity> findByProjects(Long project_seq);

//    Optional<AccountProjectEntity> deleteByUserId(String userId);
//
//    Optional<AccountProjectEntity> deleteByNpSeq(Long np_seq);
}
