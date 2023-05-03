package com.agile.demo.biz.backlog;

import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.biz.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BacklogService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public BacklogEntity createBacklog(BacklogDto backlogDto) {

        // project의 내용을 출

        Optional<ProjectEntity> projectEntity = projectRepository.findById(backlogDto.getProjectSeq());
        if (!projectEntity.isPresent()) {
            throw new EntityNotFoundException("Project not found with id " + backlogDto.getProjectSeq()); // id를 찾을 수 없는 경우 발생
        }

        BacklogEntity backlogEntity = new BacklogEntity();
        backlogEntity.setProject(projectEntity.get()); // 여기서 insert 안되는 이유 ㅜㅜ
        backlogEntity.setTitle(backlogDto.getTitle());
        backlogEntity.setDescription(backlogDto.getDescription());
        backlogEntity.setStoryProgress(backlogDto.getStoryProgress());

        return backlogRepository.save(backlogEntity);
    }

    public List<BacklogEntity> getAllBacklog() {
        return backlogRepository.findAll();
    }

    public void deleteBacklog(long nb_seq) {
        // 백로그가 존재하는지 확인
        Optional<BacklogEntity> backlogEntity = backlogRepository.findById(nb_seq);
        if (!backlogEntity.isPresent()) {
            throw new EntityNotFoundException("Backlog not found with id " + nb_seq);
        }

        // 백로그 삭제
        backlogRepository.deleteById(nb_seq);
    }

    public BacklogEntity updateBacklog(long nb_seq, BacklogDto backlogDto) {
        // np_seq 값으로 프로젝트를 조회합니다.
        BacklogEntity backlogEntity = backlogRepository.findById(nb_seq).get();

        // 프로젝트를 업데이트합니다.
        backlogEntity.setTitle(backlogDto.getTitle());
        backlogEntity.setDescription(backlogDto.getDescription());
        backlogEntity.setStoryProgress(backlogDto.getStoryProgress());


        // 업데이트된 프로젝트를 저장하고 반환합니다.
        return backlogRepository.save(backlogEntity);
    }

    public BacklogEntity getBacklogById(long nb_seq) {
        // np_seq 값으로 프로젝트를 조회합니다.
        return backlogRepository.findById(nb_seq)
                .get();
    }



}
