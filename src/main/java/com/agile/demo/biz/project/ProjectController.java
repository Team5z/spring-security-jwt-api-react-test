package com.agile.demo.biz.project;

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

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.createProject(projectDto);
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
