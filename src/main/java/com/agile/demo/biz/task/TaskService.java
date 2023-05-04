package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.account.AccountRepository;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.backlog.BacklogRepository;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.biz.project.ProjectRepository;
import com.agile.demo.biz.project.account.AccountProjectEntity;
import com.agile.demo.biz.project.account.AccountProjectRepository;
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

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AccountProjectRepository accountProjectRepository;

    public TaskEntity createTask(TaskDto taskDto) {

        // backlog에서 값을 조회해서 backlogEntity에서 받아옴
        Optional<BacklogEntity> backlogEntity = backlogRepository.findById(taskDto.getBacklogSeq());
        if (!backlogEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + taskDto.getBacklogSeq()); // id를 찾을 수 없는 경우 발생
        }

        // project에서 값을 조회해서 backlogEntity에서 받아옴
        Optional<ProjectEntity> projectEntity = projectRepository.findById(taskDto.getProjectSeq());
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Account not found with id " + taskDto.getProjectSeq()); // id를 찾을 수 없는 경우 발생
        }

        // manager, presenter의 값을 하나씩 조회 -> 리스트로 만들기
        Optional<AccountProjectEntity> accountProjectEntityManager = accountProjectRepository.findByAccounts_UserId(taskDto.getManager());
        if (!accountProjectEntityManager.isPresent()) {
            throw new EntityNotFoundException("AccountProject not found with Manger Id " + taskDto.getManager()); // id를 찾을 수 없는 경우 발생
        }

        Optional<AccountProjectEntity> accountProjectEntityPresenter = accountProjectRepository.findByAccounts_UserId(taskDto.getPresenter());
        if (!accountProjectEntityPresenter.isPresent()) {
            throw new EntityNotFoundException("AccountProject not found with Presenter Id " + taskDto.getPresenter()); // id를 찾을 수 없는 경우 발생
        }
        
        // 리스트에 0번 - Presenter, 1번 - Manager를 저장
//        List<AccountProjectEntity> accountProjectList = new ArrayList<>();
//        accountProjectList.add(accountProjectEntityPresenter);
//        accountProjectList.add(accountProjectEntityManager);


        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setSeq(taskDto.getNt_seq());
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setStoryProgress(taskDto.getStoryProgress());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setDeadline(taskDto.getDeadline()); // 작성자가 원하는 시간으로
        taskEntity.setPresenter(accountProjectEntityPresenter.get());
        taskEntity.setManager(accountProjectEntityManager.get());
        //taskEntity.setAccountrPoject(accountProjectList.get(0));
        taskEntity.setBacklogEntity(backlogEntity.get());
        taskEntity.setProject(projectEntity.get());
        // BacklogEntity와 TaskEntity는 cascade 옵션으로 인해 자동 저장됩니다.
        return taskRepository.save(taskEntity);
    }

    public List<TaskEntity> getAllTasks() { // 존재하는 모든 Task 출력
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities;
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
        //taskEntity.setBacklogEntity(taskDto.getBacklogSeq());

//
//        taskEntity.setPresenter(taskDto.getPresenter());
//        taskEntity.setManager(taskDto.getManager());
        taskEntity.setStoryProgress(taskDto.getStoryProgress());
        taskEntity.setDeadline(taskDto.getDeadline());

        // 업데이트된 태스크를 저장하고 반환합니다.
        return taskRepository.save(taskEntity);
    }

}
