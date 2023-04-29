package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.backlog.BacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public AccountEntity createBacklog(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(accountDto.getUserId());
        accountEntity.setName(accountDto.getName());
        accountEntity.setRole(accountDto.getRole());
        accountEntity.setEmail(accountDto.getEmail());
        accountEntity.setPhone(accountDto.getPhone());
        accountEntity.setPassword(accountDto.getPassword());

        return accountRepository.save(accountEntity);
    }
}
