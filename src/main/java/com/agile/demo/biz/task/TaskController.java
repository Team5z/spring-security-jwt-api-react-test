package com.agile.demo.biz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        TaskEntity taskEntity = taskService.createTask(taskDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
        public List<TaskEntity> getAllTask() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("{nt_seq}")
    public ResponseEntity deleteTask(@PathVariable long nt_seq){
        taskService.deleteTask(nt_seq);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{np_seq}") // 태스크 출력하기 - 프로젝트별
    public ResponseEntity<TaskEntity> getProjectIdTask(@PathVariable long np_seq) {
        TaskEntity taskEntity = taskService.getTaskById(np_seq);
        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping("{nt_seq}") // 태스크 출력하기 - 1개만
    public ResponseEntity<TaskEntity> getIdTask(@PathVariable long nt_seq) {
        TaskEntity taskEntity = taskService.getTaskById(nt_seq);
        return ResponseEntity.ok(taskEntity);
    }

    @PutMapping("{nt_seq}") // 테스크 - 정보수정
    public ResponseEntity<?> updateTask(@PathVariable long nt_seq, @RequestBody TaskDto taskDto) {
        TaskEntity taskEntity = taskService.updateTask(nt_seq, taskDto);
        return ResponseEntity.ok(taskEntity);
    }

}
