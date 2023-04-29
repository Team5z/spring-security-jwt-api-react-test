package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
