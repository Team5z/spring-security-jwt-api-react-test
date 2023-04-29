package com.agile.demo.biz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        TaskEntity taskEntity = taskService.createTask(taskDto);
        return ResponseEntity.created(URI.create("/task/" + taskEntity.getNt_seq())).build();
    }

    @GetMapping("/tasks")
        public List<TaskDto> getAllTask() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("task/{nt_seq}")
    public void deleteTask(@PathVariable long nt_seq){
        taskService.deleteTask(nt_seq);
    }

    @GetMapping("/tasks/{np_seq}") // 태스크 출력하기 - 프로젝트별
    public ResponseEntity<TaskDto> getProjectIdTask(@PathVariable long np_seq) {
        TaskDto taskDto = convertToDto(taskService.getTaskById(np_seq));
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/tasks/{nt_seq}") // 태스크 출력하기 - 1개만
    public ResponseEntity<TaskDto> getIdTask(@PathVariable long nt_seq) {
        TaskDto taskDto = convertToDto(taskService.getTaskById(nt_seq));
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/task/{nt_seq}")
    public ResponseEntity<?> updateTask(@PathVariable long nt_seq, @RequestBody TaskDto taskDto) {
        TaskEntity taskEntity = taskService.updateTask(nt_seq, taskDto);
        return ResponseEntity.ok(convertToDto(taskEntity));
    }

    private TaskDto convertToDto(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setNt_seq(taskEntity.getNt_seq());
       //taskDto.setBacklogEntity(BacklogEntity.getP.getNb_seq());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setPresenter(taskEntity.getPresenter());
        taskDto.setManager(taskEntity.getManager());
        taskDto.setCreate_date(taskEntity.getCreate_date());
        taskDto.setUpdate_data(taskEntity.getUpdate_data());
        taskDto.setStory_progress(taskEntity.getStory_progress());
        //taskDto.setProjectEntity(taskEntity.getProjectEntity());

        return taskDto;
    }
}
