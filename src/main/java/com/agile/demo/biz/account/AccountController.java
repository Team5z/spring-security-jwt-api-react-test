package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.backlog.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @PostMapping("/acount") // 백로그 생성하기
//    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) {
//        AccountEntity accountEntity = accountService.createAccount(accountDto);
//
//        return ResponseEntity.created(URI.create("/account/" + accountEntity.getUserId())).build();
//    }


}
