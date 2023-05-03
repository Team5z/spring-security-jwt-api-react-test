package com.agile.demo.biz.account;

import com.agile.demo.biz.backlog.BacklogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 구글에서 정보 받아와서 작업 하는 이유로 -> 잠시 멈추기
    // 계정 생성시 - 정보 저장, 동일한 id로 가입할 수 없음
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto AccountDto) {
        // 미리 동일한 id가 있는지 확인하는 로직이 필요할지? - 동일 아이디 존재 여부 확인 안하면 넣을때 오류가 발생함!
        // => 동일한 id가 있으면 아이디가 존재한다고 ㄱㄱ
        // return ResponseEntity.실패 이런식으로 if문 안에 작성할 것


        // 회원 가입에 입력한 정보 저장
        // 비밀번호를 저장할때 암호화해서 저장해야하는지 여부 필요
        AccountEntity accountEntity = accountService.createAccount(AccountDto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping // Account의 전체 내용 출력
    public List<AccountEntity> getAllBacklog() {
        return accountService.getAllAccounts();
    }

    @GetMapping("{userId}") // Account의 전체 내용 출력
    public ResponseEntity<AccountEntity> getUserIdAccount(@PathVariable String userId) {
        AccountEntity accountEntity = accountService.getAccountById(userId);
        return ResponseEntity.ok(accountEntity);
    }
    
    // update 하는 내용 추가 필요


    @DeleteMapping("/{userId}")
    public void deleteAccount(@PathVariable String userId){
        accountService.deleteAccount(userId);
    }

}
