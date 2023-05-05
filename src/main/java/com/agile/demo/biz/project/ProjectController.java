package com.agile.demo.biz.project;

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
        // AccountProject도 추가하기
          // accountProject와 타입이 안맞는 문제 발생함
        return ResponseEntity.accepted().build();
    }
    
    // 프로젝트에 초대하는 경우
    @PostMapping("/invite") // 프로젝트에 초대하는 경우
    public ResponseEntity<?> inviteProject(@RequestBody ProjectDto projectDto) { // userId, ProjectSeq 필요
        // AccountProject도 추가하기
        //accountProjectService.createAccountProject(projectDto);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping
    public ResponseEntity getAllProjects() {
        return ResponseEntity.ok().body(projectService.getAllProjects()) ;
    }

    @GetMapping("{np_seq}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable long np_seq) {
        ProjectEntity projectEntity =projectService.getProjectById(np_seq);
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
