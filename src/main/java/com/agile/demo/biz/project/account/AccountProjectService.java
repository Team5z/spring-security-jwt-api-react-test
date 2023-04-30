package com.agile.demo.biz.project.account;


import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.account.AccountRepository;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.biz.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public void saveAccountToProject(String userId, Long projectId) {
        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(userId);
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + userId);
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(projectId);
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + projectId);
        }

        AccountProjectEntity accountProjectEntity = new AccountProjectEntity();
        accountProjectEntity.setAccounts(accountEntity.get());
        accountProjectEntity.setProjects(projectEntity.get());
        accountProjectRepository.save(accountProjectEntity);
    }

    //삭제하는 부분
    // 1. 계정 삭제, 프로젝트 탈퇴 - 개인 정보만 삭제하는 경우


    // 2. 프로젝트 삭제 - 프로젝트내 속한 사람들의 정보 제거


}
