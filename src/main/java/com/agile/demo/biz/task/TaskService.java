package com.agile.demo.biz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity createTask(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setNt_seq(taskDto.getNt_seq());
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setStory_progress(taskDto.getStory_progress());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setCreate_date(taskDto.getCreate_date()); // 현재 시간을 작성
        taskEntity.setUpdate_data(taskDto.getUpdate_date()); // 현재 시간을 작성
        taskEntity.setDeadline(taskDto.getDeadline()); // 작성자가 원하는 시간으로
        taskEntity.setPresenter(taskDto.getPresenter());
        taskEntity.setManager(taskDto.getManager());

        // BacklogEntity와 TaskEntity는 cascade 옵션으로 인해 자동 저장됩니다.
        return taskRepository.save(taskEntity);
    }

    public List<TaskDto> getAllTasks() { // 존재하는 모든 Task 출력
        List<TaskEntity> taskEntities = taskRepository.findAll();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            TaskDto taskDto = new TaskDto();
            taskDto.setNt_seq(taskEntity.getNt_seq());
            taskDto.setTitle(taskEntity.getTitle());
            taskDto.setDescription(taskEntity.getDescription());
            taskDto.setStory_progress(taskEntity.getStory_progress());
            taskDto.setCreate_date(taskEntity.getCreate_date());
            taskDto.setUpdate_date(taskEntity.getUpdate_data());
            taskDto.setDeadline(taskEntity.getDeadline().toString());
            taskDto.setPresenter(taskEntity.getPresenter());
            taskDto.setManager(taskEntity.getManager());


            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    // 특정 project에서 task 조회하기
    public TaskEntity getTaskByProjectId(long np_seq) {
        // nt_seq 값으로 태스크를 조회합니다.
        return taskRepository.findById(np_seq)
                .get();
    }
    
    // 특정 id의 task 조회
    public TaskEntity getTaskById(long nt_seq) {
        // nt_seq 값으로 태스크를 조회합니다.
        return taskRepository.findById(nt_seq)
                .get();
    }

    public void deleteTask(long nt_seq) {
        // 프로젝트가 존재하는지 확인
        Optional<TaskEntity> taskEntity = taskRepository.findById(nt_seq);
        if (!taskEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + nt_seq);
        }

        // 프로젝트 삭제
        taskRepository.deleteById(nt_seq);
    }
    public TaskEntity updateTask(long nt_seq, TaskDto taskDto) {
        // nt_seq 값으로 태스크를 조회합니다.
        TaskEntity taskEntity = taskRepository.findById(nt_seq).get();

        // 태스크를 업데이트합니다.
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setBacklogEntity(taskDto.getBacklogEntity());
        taskEntity.setPresenter(taskDto.getPresenter());
        taskEntity.setManager(taskDto.getManager());
        taskEntity.setUpdate_data(taskDto.getUpdate_date());
        taskEntity.setStory_progress(taskDto.getStory_progress());
        taskEntity.setAccount(taskDto.getAccount());
        taskEntity.setDeadline(taskDto.getDeadline());

        // 업데이트된 태스크를 저장하고 반환합니다.
        return taskRepository.save(taskEntity);
    }

   


}
