package com.agile.demo.biz.backlog;
import com.agile.demo.biz.project.ProjectEntity;
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
public class BacklogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nb_seq", nullable = false, length = 25, unique = true)
    private Long nb_seq;

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private int story_progress;

    @Column(nullable = true, updatable = true, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "np_seq", insertable = false, updatable = false)
    private ProjectEntity project;

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}