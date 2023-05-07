package com.agile.demo.biz.project.account;


import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.account.AccountRepository;
import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.biz.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountProjectService {

    @Autowired
    private AccountProjectRepository accountProjectRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProjectRepository projectRepository;

    // 지금 이렇게 사용하면 Account와 Project의 내용이 훼손 위험이 있는지 확인 필요

    // 생성
    // 1. 프로젝트 생성시 추가
    // 2. 프로젝트에 초대 받은 경우 추가
    public AccountProjectEntity createAccountProject(AccountProjectDto accountProjectDto) {

        // 1. 계쩡과 프로젝트의 존재 여부 확인
        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(accountProjectDto.getAccountUserId());
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + accountProjectDto.getAccountUserId()); // id를 찾을 수 없는 경우 발생
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(accountProjectDto.getProjectSeq());
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + accountProjectDto.getProjectSeq());
        }

        // 2. AccountProject에서 중복확인
        // 여기에 중복확인 필요함 => 중복된 결과 있으면 return null; 
        // 어떻게 처리할지 고민중
        Long count = accountProjectRepository.findUser(accountProjectDto.getAccountUserId(), accountProjectDto.getProjectSeq());
        if(count >= 1) // 프로젝트에 가입한 사람이면 생성하지 않음
        {
            return null;
        }


        AccountProjectEntity accountProjectEntity = new AccountProjectEntity();
        accountProjectEntity.setAccounts(accountEntity.get());
        accountProjectEntity.setProjects(projectEntity.get());
        return     accountProjectRepository.save(accountProjectEntity);

    }

    public List<AccountProjectEntity> getAllaccountProject() {
        return accountProjectRepository.findAll();
    }

    public List<AccountProjectEntity> getAccountProjectByNp_seq(Long np_seq){
        List<AccountProjectEntity> accountProjectEntities = Arrays.asList(accountProjectRepository.findByProjects_Seq(np_seq).orElse(null));
        return accountProjectEntities;
    }

    //삭제하는 부분
    // 1. 계정 삭제, 프로젝트 탈퇴 - 개인 정보만 삭제하는 경우
    @Transactional
    public void deleteAccountProject_userId(String userId) {
        // accountproject에 존재하는 userId인지를 확인하고 삭제!

        Optional<AccountProjectEntity> accountProjectEntity = accountProjectRepository.findByAccounts_UserId(userId);
        if (!accountProjectEntity.isPresent()) {
            // project에 가입이 안된 상태
            System.out.println("null");
        }else{
            // project에 가입된 경우로, AccountProject에서 삭제
            accountProjectRepository.deleteByAccounts_UserId(userId);
        }

    }

    // 2. 프로젝트 삭제 - 프로젝트내 속한 사람들의 정보 제거
    // project에서 cascade설정해서 같이 삭제 됨

}
