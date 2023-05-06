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
@RequestMapping("/accountprojects") // 테스트용으로 곧 제거할 예정
public class AccountProjectController {

    @Autowired
    private AccountProjectService accountProjectService;

    @PostMapping// AccountProject 생성하기
    public ResponseEntity<?> createAccountProject(@RequestBody AccountProjectDto accountProjectDto) {
        AccountProjectEntity accountProjectEntity = accountProjectService.createAccountProject(accountProjectDto);

        return ResponseEntity.accepted().build();
    }
    
    @GetMapping // AccountProject 전체 내용 조회하기
    public List<AccountProjectEntity> getAllAccountProject() {
        return accountProjectService.getAllaccountProject();
    }

    @GetMapping("{np_seq}") // AccountProject에서 np_seq로 조회한 결과
    public List<AccountProjectEntity> getAccountProjectByNp_Seq(@PathVariable Long np_seq){
        return accountProjectService.getAccountProjectByNp_seq(np_seq);
    }

    @DeleteMapping("/{userId}") // Account에서 UserId 삭제시 진행
    public ResponseEntity deleteAccountProject(@PathVariable String userId){
        accountProjectService.deleteAccountProject_userId(userId);
        return ResponseEntity.accepted().build();
    }

}
