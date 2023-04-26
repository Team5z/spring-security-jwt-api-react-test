package com.agile.demo.biz.project;


import com.agile.demo.biz.accountproject.AccountProjectEntity;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.task.TaskEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_AGL_ACCOUNT")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProjectEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="np_seq", nullable = false, length = 25, unique = true)
    private long np_seq;

    @Column(nullable = false, updatable = true, length = 100)
    private String project_title;

    @Column(nullable = true, updatable = true)
    private String project_assign;


    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="nb_seq", referencedColumnName="nb_seq")
    private List<BacklogEntity> backlogs;       // 체크 필요

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="nt_seq", referencedColumnName="nt_seq")
    private List<TaskEntity> task;

    @OneToOne
    @JoinColumn(name="np_seq", referencedColumnName="np_seq")
    private AccountProjectEntity accountProjects;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    @JoinColumn(name="nap_seq")
//    private List<AccountProjectEntity> accountproject;
}
