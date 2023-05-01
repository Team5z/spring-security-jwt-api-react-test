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

import javax.persistence.EntityNotFoundException;
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

        System.out.println(accountProjectDto.getAccounts()); // AccountEntity(userId=sd1, password=null, role=null, name=null, phone=null, email=null)
        System.out.println(accountProjectDto.getProjects()); // null // projects의 내용은 안 들어감 - Dto의 오타

        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(accountProjectDto.getAccounts().getUserId());
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + accountProjectDto.getAccounts().getUserId()); // id를 찾을 수 없는 경우 발생
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(accountProjectDto.getProjects().getNp_seq());
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + accountProjectDto.getProjects().getNp_seq());
        }

        // id와 np_seq를 조회한 결과를 accountprojectDto에 넣기
        accountProjectDto.setAccounts(accountEntity.get());
        accountProjectDto.setProjects(projectEntity.get());

        AccountProjectEntity accountProjectEntity = new AccountProjectEntity();
        accountProjectEntity.setAccounts(accountProjectDto.getAccounts());
        accountProjectEntity.setProjects(accountProjectDto.getProjects());
        return     accountProjectRepository.save(accountProjectEntity);

    }

//    public List<AccountProjectDto> getAllaccountProject() {
//        List<AccountProjectEntity> accountProjectEntities = accountProjectRepository.findAll();
//        List<AccountProjectDto> accountProjectDtos = new ArrayList<>();
//        for (AccountProjectEntity accountProjectEntity : accountProjectEntities) {
//            AccountProjectDto accountProjectDto = new AccountProjectDto();
//            accountProjectDto.setAccounts(accountProjectEntity.getAccounts());
//            accountProjectDto.setProjects(accountProjectEntity.getProjects());
//            accountProjectDtos.add(accountProjectDto);
//        }
//        return accountProjectDtos;
//    }

    public List<AccountProjectEntity> getAllaccountProject() {
        return accountProjectRepository.findAll();
    }

    //삭제하는 부분
    // 1. 계정 삭제, 프로젝트 탈퇴 - 개인 정보만 삭제하는 경우


    // 2. 프로젝트 삭제 - 프로젝트내 속한 사람들의 정보 제거


}
