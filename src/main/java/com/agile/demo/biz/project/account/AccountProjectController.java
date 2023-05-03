package com.agile.demo.biz.project.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.backlog.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accountproject")
public class AccountProjectController {

    @Autowired
    private AccountProjectService accountProjectService;

    @PostMapping// AccountProject 생성하기
    public ResponseEntity<?> createAccountProject(@RequestBody AccountProjectDto accountProjectDto) {
        AccountProjectEntity accountProjectEntity = accountProjectService.createAccountProject(accountProjectDto);

        return ResponseEntity.accepted().build();
    }

    // 내용이 잘들어갔는지 확인용
    @GetMapping // 백로그 출력하기 - 전체
    public List<AccountProjectEntity> getAllAccountProject() {
        return accountProjectService.getAllaccountProject();
    }

    @DeleteMapping("/{userId}")
    public void deleteAccountProject(@PathVariable String userId){
        accountProjectService.deleteAccountProject_userId(userId);
    }

//    @DeleteMapping("/{np_seq}")
//    public void deleteAccountProject(@PathVariable Long  np_seq){
//        accountProjectService.deleteAccountProject_npSeq(np_seq);
//    }
}
