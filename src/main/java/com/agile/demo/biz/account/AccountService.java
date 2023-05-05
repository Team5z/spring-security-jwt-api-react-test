package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.account.AccountProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountProjectService accountProjectService;

    // AccountEntity 생성하기
    public AccountEntity createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(accountDto.getUserId());
        accountEntity.setName(accountDto.getName());
        accountEntity.setPassword(accountDto.getPassword());
        accountEntity.setPhone(accountDto.getPhone());
        //accountEntity.setRole(accountDto.getRole());
        accountEntity.setEmail(accountDto.getEmail());

        return accountRepository.save(accountEntity);
    }

    // AccountEntity의 전체 내용 가져오기
    public List<AccountEntity> getAllAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return accountEntities;
    }

    // AccountEntity에서 userId로 조회한 내용 가져오기
    public AccountEntity getAccountByUserId(String userId) {
        // np_seq 값으로 프로젝트를 조회합니다.
        return accountRepository.findByUserId(userId)
                .get();
    }

    // AccountEntity에서 userId 삭제하기
    public void deleteAccount(String userId){
        // Project에 가입된 경우 userId가 바록 삭제 되지 않음
        // 1. Project에 가입여부 확인 
        // 1-1 가입된 경우 AccountProject에서 가입된 userId 삭제
        accountProjectService.deleteAccountProject_userId(userId);

        // 1-2 project에 가입을 안했거나 AccountProject에서 가입 내용 삭제 후 userId 삭제
        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(userId);
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + userId);
        }

        // account 삭제
        accountRepository.deleteByUserId(userId);
    }

}
