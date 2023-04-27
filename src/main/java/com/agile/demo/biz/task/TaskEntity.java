package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private int story_progress;

    @Column(nullable = true, updatable = true, length = 255)
    private String description;

    @Column(nullable = true, updatable = true)
    private Long assign;

    @Column(nullable = false, updatable = true)
    private Long presenter;

    @Column(nullable = false, updatable = true)
    private Long manager;

    @OneToOne
    @JoinColumn(name = "account_seq", referencedColumnName = "seq")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "project_seq", referencedColumnName = "seq")
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name = "backlog_seq", referencedColumnName = "seq")
    private BacklogEntity backlogEntity;
}
