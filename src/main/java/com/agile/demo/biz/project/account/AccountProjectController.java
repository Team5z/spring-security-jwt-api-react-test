package com.agile.demo.biz.project.account;

import com.agile.demo.biz.backlog.BacklogDto;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.backlog.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accountproject")
public class AccountProjectController {

    @Autowired
    private AccountProjectService accountProjectService;

    @PostMapping// 백로그 생성하기
    public ResponseEntity<?> createProject(@RequestBody AccountProjectDto accountProjectDto) {
        AccountProjectEntity accountProjectEntity = accountProjectService.createAccountProject(accountProjectDto);

        return ResponseEntity.created(URI.create("/accountProject/" + accountProjectEntity.getSeq())).build();
    }

//    @DeleteMapping("{userId}")
//    public void deleteAccount(@PathVariable String userId){
//        accountProjectService.deleteAccount(userId);
//    }
}
