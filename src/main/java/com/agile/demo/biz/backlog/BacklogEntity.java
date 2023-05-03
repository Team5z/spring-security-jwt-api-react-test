package com.agile.demo.biz.backlog;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "NBacklog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogEntity extends BaseEntity {

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private Integer storyProgress;

    @Column(nullable = true, updatable = true, length = 255)
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_seq", referencedColumnName = "seq")
    private ProjectEntity project; // ProjectEntity의 mapper by와 이름을 맞춤

}