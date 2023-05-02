package com.agile.demo.biz.backlog;

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
    public BacklogEntity createBacklog(BacklogDto backlogDto) {
        BacklogEntity backlogEntity = new BacklogEntity();
        backlogEntity.setProject(backlogDto.getProject()); // 지금 프로젝트의 번호를 넣어야함 - 외래키를 넣음?
        backlogEntity.setTitle(backlogDto.getTitle());
        backlogEntity.setDescription(backlogDto.getDescription());
        backlogEntity.setStory_progress(backlogDto.getStory_progress());

        return backlogRepository.save(backlogEntity);
    }

    public List<BacklogDto> getAllBacklog() {
        List<BacklogEntity> backlogEntities = backlogRepository.findAll();
        List<BacklogDto> backlogDtos = new ArrayList<>();
        for (BacklogEntity backlogEntity : backlogEntities) {
            BacklogDto backlogDto = new BacklogDto();
            backlogDto.setNb_seq(backlogEntity.getNb_seq());
            backlogDto.setTitle(backlogEntity.getTitle());
            backlogDto.setStory_progress(backlogEntity.getStory_progress());
            backlogDto.setDescription(backlogEntity.getDescription());
            backlogDto.setProject(backlogEntity.getProject());
            backlogDtos.add(backlogDto);
        }
        return backlogDtos;
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
        backlogEntity.setStory_progress(backlogDto.getStory_progress());


        // 업데이트된 프로젝트를 저장하고 반환합니다.
        return backlogRepository.save(backlogEntity);
    }

    public BacklogEntity getBacklogById(long nb_seq) {
        // np_seq 값으로 프로젝트를 조회합니다.
        return backlogRepository.findById(nb_seq)
                .get();
    }



}
