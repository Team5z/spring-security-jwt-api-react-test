package com.agile.demo.biz.backlog;

import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_AGL_BACKLOG")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BacklogEntity extends BaseEntity implements Serializable {

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

    @Column(nullable = true, updatable = true)
    private Long assign;

//    @ManyToOne
//    @JoinColumn(name = "np_seq", insertable = false, updatable = false)
//    private ProjectEntity project;
}
