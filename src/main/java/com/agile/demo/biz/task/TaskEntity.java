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
@Table(name = "T_AGL_TASK")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nt_seq", nullable = false, length = 25, unique = true)
    private Long nt_seq;

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private int story_progress;

    @Column(nullable = true, updatable = true, length = 255)
    private String description;

    @Column(nullable = true, updatable = true)
    private Long assign;

    @Column(name = "createDate", nullable = true, insertable = true, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime create_date;

    @Column(name = "updateData", nullable = true, insertable = true, updatable = true)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime update_data;

    @Column(nullable = false, updatable = true)
    private Long presenter;

    @Column(nullable = false, updatable = true)
    private Long manager;

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, unique = true, updatable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "np_seq", insertable = false, unique = true, updatable = false)
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name = "nb_seq", insertable = false, unique = true, updatable = false)
    private BacklogEntity backlogEntity;
}
