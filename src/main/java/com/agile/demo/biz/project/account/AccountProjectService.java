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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
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

        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(accountProjectDto.getAccountUserId());
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + accountProjectDto.getAccountUserId()); // id를 찾을 수 없는 경우 발생
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(accountProjectDto.getProjectSeq());
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + accountProjectDto.getProjectSeq());
        }


        AccountProjectEntity accountProjectEntity = new AccountProjectEntity();
        accountProjectEntity.setAccounts(accountEntity.get());
        accountProjectEntity.setProjects(projectEntity.get());
        return     accountProjectRepository.save(accountProjectEntity);

    }

    public List<AccountProjectEntity> getAllaccountProject() {
        return accountProjectRepository.findAll();
    }

    //삭제하는 부분
    // 1. 계정 삭제, 프로젝트 탈퇴 - 개인 정보만 삭제하는 경우
    @Transactional
    public void deleteAccountProject_userId(String userId) {
        // 프로젝트가 존재하는지 확인

        Optional<AccountProjectEntity> accountProjectEntity = accountProjectRepository.findByAccounts_UserId(userId);
        if (!accountProjectEntity.isPresent()) {
            throw new EntityNotFoundException("AccountProject not found with id " + userId);
        }

        // 프로젝트 삭제
        accountProjectRepository.deleteByAccounts_UserId(userId);
    }

    // 2. 프로젝트 삭제 - 프로젝트내 속한 사람들의 정보 제거
//    @Transactional
//    public void deleteAccountProject_npSeq(String userId) {
//        // 프로젝트가 존재하는지 확인
//
//        Optional<AccountProjectEntity> accountProjectEntity = accountProjectRepository.findByAccounts_UserId(userId);
//        if (!accountProjectEntity.isPresent()) {
//            throw new EntityNotFoundException("AccountProject not found with id " + userId);
//        }
//
//        // 프로젝트 삭제
//        accountProjectRepository.deleteByAccounts_UserId(userId);
//    }

}
