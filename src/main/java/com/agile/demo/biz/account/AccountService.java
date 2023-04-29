package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
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

    public AccountEntity createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(accountDto.getUserId());
        accountEntity.setName(accountDto.getName());
        accountEntity.setPassword(accountDto.getPassword());
        accountEntity.setPhone(accountDto.getPhone());
        accountEntity.setRole(accountDto.getRole());
        accountEntity.setEmail(accountDto.getEmail());

        return accountRepository.save(accountEntity);
    }


    public List<AccountDto> getAllAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            AccountDto accountDto = new AccountDto();
            accountDto.setUserId(accountEntity.getUserId());
            accountDto.setPassword(accountEntity.getPassword());
            accountDto.setRole(accountEntity.getRole());
            accountDto.setName(accountEntity.getName());
            accountDto.setPhone(accountEntity.getPhone());
            accountDto.setEmail(accountEntity.getEmail());
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }

    public AccountEntity getAccountById(String userId) {
        // np_seq 값으로 프로젝트를 조회합니다.
        return accountRepository.findByUserId(userId)
                .get();
    }

    public void deleteAccount(String userId){
        Optional<AccountEntity> accountEntity = accountRepository.findByUserId(userId);
        if (!accountEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + userId);
        }

        // 백로그 삭제
        accountRepository.deleteByUserId(userId);
    }

}
