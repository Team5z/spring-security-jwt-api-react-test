package com.agile.demo.biz.project;

import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.account.AccountProjectEntity;
import com.agile.demo.biz.task.TaskEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProjectEntity extends BaseEntity{

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private LocalDateTime endAt;


    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<BacklogEntity> backlogs;       // 체크 필요

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<AccountProjectEntity> accountProjects;

}
