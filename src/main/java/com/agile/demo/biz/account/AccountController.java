package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 계정 생성시 - 정보 저장, 동일한 id로 가입할 수 없음
    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto AccountDto) {
        // 미리 동일한 id가 있는지 확인하는 로직이 필요할지? - 동일 아이디 존재 여부 확인 안하면 넣을때 오류가 발생함!
        // => 동일한 id가 있으면 아이디가 존재한다고 ㄱㄱ
        // return ResponseEntity.실패 이런식으로 if문 안에 작성할 것

        // 회원 가입에 입력한 정보 저장
        AccountEntity accountEntity = accountService.createAccount(AccountDto);

        return ResponseEntity.created(URI.create("/account/" + accountEntity.getUserId())).build();
    }

    @GetMapping("/account") // Account의 전체 내용 출력
    public List<AccountDto> getAllBacklog() {
        return accountService.getAllAccounts();
    }



}
