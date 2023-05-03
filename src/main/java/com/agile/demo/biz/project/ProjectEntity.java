package com.agile.demo.biz.project;

import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.task.TaskEntity;
import com.agile.demo.core.base.BaseEntity;
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

public class ProjectEntity extends BaseEntity {

    @Column(nullable = false, updatable = true, length = 100)
    private String project_title;

    @Column(nullable = true, updatable = true)
    private String project_assign;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project") // project를 삭제하면 같이 삭제1
    private List<BacklogEntity> backlogs;       // 체크 필요


}