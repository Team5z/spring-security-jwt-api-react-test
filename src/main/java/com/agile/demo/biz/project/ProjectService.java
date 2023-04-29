package com.agile.demo.biz.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class ProjectService {

        @Autowired
        private ProjectRepository projectRepository;

        public ProjectEntity saveProject(ProjectDto projectDto) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setProject_title(projectDto.getProject_title());
            projectEntity.setProject_assign(projectDto.getProject_assign());
            // BacklogEntity와 TaskEntity는 cascade 옵션으로 인해 자동 저장됩니다.
            return projectRepository.save(projectEntity);
        }

        public List<ProjectDto> getAllProjects() { // entity -> dto로 값을 넣는 과정
            List<ProjectEntity> projectEntities = projectRepository.findAll();
            List<ProjectDto> projectDtos = new ArrayList<>();
            for (ProjectEntity projectEntity : projectEntities) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setNp_seq(projectEntity.getNp_seq());
                projectDto.setProject_title(projectEntity.getProject_title());
                projectDto.setProject_assign(projectEntity.getProject_assign());
                projectDtos.add(projectDto);
            }
            return projectDtos;
        }

        public void deleteProjectById(Long np_seq) {
            projectRepository.deleteById(np_seq);
        }

        public ProjectEntity createProject(ProjectDto projectDto) {
            ProjectEntity project = new ProjectEntity();
            project.setProject_title(projectDto.getProject_title());
            project.setProject_assign(projectDto.getProject_assign());
            return projectRepository.save(project);
        }

    public ProjectEntity updateProject(long np_seq, ProjectDto projectDto) {
        // np_seq 값으로 프로젝트를 조회합니다.
        ProjectEntity projectEntity = projectRepository.findById(np_seq).get();

        // 프로젝트를 업데이트합니다.
        projectEntity.setProject_title(projectDto.getProject_title());
        projectEntity.setProject_assign(projectDto.getProject_assign());

        // 업데이트된 프로젝트를 저장하고 반환합니다.
        return projectRepository.save(projectEntity);
    }

        public void deleteProject(long np_seq) {
            // 프로젝트가 존재하는지 확인
            Optional<ProjectEntity> projectEntity = projectRepository.findById(np_seq);
            if (!projectEntity.isPresent()) {
                throw new EntityNotFoundException("Project not found with id " + np_seq);
            }

            // 프로젝트 삭제
            projectRepository.deleteById(np_seq);
        }

    public ProjectEntity getProjectById(long np_seq) {
        // np_seq 값으로 프로젝트를 조회합니다.
        return projectRepository.findById(np_seq)
                .get();
    }
}



