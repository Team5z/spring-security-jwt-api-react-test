package com.agile.demo.biz.project;

import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.task.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NProject")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProjectEntity {

    @Id
    @JsonManagedReference
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="np_seq", nullable = false, length = 25, unique = true)
    private long np_seq;

    @Column(nullable = false, updatable = true, length = 100)
    private String project_title;

    @Column(nullable = true, updatable = true)
    private String project_assign;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<BacklogEntity> backlogs;       // 체크 필요

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<TaskEntity> taskentity;

    public static ProjectEntity valueOf(String value) { // 추가해서 backlog create 테스트 성공함
        Long np_seq = Long.valueOf(value);
        return ProjectEntity.builder().np_seq(np_seq).build();
    }

}