package com.agile.demo.biz.project;

import com.agile.demo.biz.project.account.AccountProjectDto;
import com.agile.demo.biz.project.account.AccountProjectEntity;
import com.agile.demo.biz.project.account.AccountProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountProjectService accountProjectService;

    @PostMapping // 프로젝트 생성하는 경우
    public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.createProject(projectDto);
        System.out.println("entity 성공");

        // AccountProject에 추가하기
        AccountProjectDto accountProjectDto  = new AccountProjectDto();
        accountProjectDto.setAccountUserId(projectEntity.getAssign());
        accountProjectDto.setProjectSeq(projectEntity.getSeq());
        accountProjectService.createAccountProject(accountProjectDto);

        return ResponseEntity.accepted().build();
    }
    

    @PostMapping("/invite") // 프로젝트에 초대하는 경우
    public ResponseEntity<?> inviteProject(@RequestBody ProjectDto projectDto) { // userId, ProjectSeq 필요
        // AccountProject도 추가하기
        AccountProjectDto accountProjectDto  = new AccountProjectDto();
        accountProjectDto.setAccountUserId(projectDto.getAssign());
        accountProjectDto.setProjectSeq(projectDto.getNp_seq());
        accountProjectService.createAccountProject(accountProjectDto);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping
    public ResponseEntity getAllProjects() {
        return ResponseEntity.ok().body(projectService.getAllProjects()) ;
    }

    @GetMapping("{np_seq}")
    public ResponseEntity<ProjectEntity> getProjectByNp_seq(@PathVariable long np_seq) {
        ProjectEntity projectEntity =projectService.getProjectByNp_seq(np_seq);
        return ResponseEntity.ok(projectEntity);
    }

    // 특정 값을의 내용을 수정
    @PutMapping("{np_seq}")
    public ResponseEntity<?> updateProject(@PathVariable long np_seq, @RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.updateProject(np_seq, projectDto);
        return ResponseEntity.ok(projectEntity);
    }

    @DeleteMapping("{np_seq}")
    public ResponseEntity deleteProject(@PathVariable long np_seq){
        projectService.deleteProject(np_seq);
        return ResponseEntity.accepted().build();
    }

}
