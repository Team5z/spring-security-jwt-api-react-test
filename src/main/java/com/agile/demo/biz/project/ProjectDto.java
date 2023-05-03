package com.agile.demo.biz.project;

import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.task.TaskEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private long np_seq;

    private String title;

    private String assign;

    private List<BacklogEntity> backlogs;

    private List<TaskEntity> tasks;
}
