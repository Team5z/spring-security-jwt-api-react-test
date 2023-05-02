package com.agile.demo.biz.task;
import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "NTask")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nt_seq", nullable = false, length = 25, unique = true)
    private Long nt_seq;

    @Column(nullable = false, updatable = true, length = 100)
    private String title;

    @Column(nullable = true, updatable = true)
    private Integer story_progress;

    @Column(nullable = true, updatable = true, length = 255)
    private String description;

    @Column(name = "createDate", nullable = true, insertable = true, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime create_date;

    @Column(name = "updateData", nullable = true, insertable = true, updatable = true)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime update_data;

    @PrePersist
    public void setDate() {
        this.setCreate_date(LocalDateTime.now());
        this.setUpdate_data(LocalDateTime.now());
    }

    @PreUpdate
    public void modifiedDate() {
        this.setUpdate_data(LocalDateTime.now());
    }

    // deadline 추가하기 - dropdown 형식으로 보이도록할 예정
    @Column(nullable = true, updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate deadline;

    // accountProjectEntity에서 가져오도록?
    @Column(nullable = false, updatable = true)
    private Long presenter;

    @Column(nullable = false, updatable = true)
    private Long manager;

    @OneToOne
    @JoinColumn(name = "na_seq", insertable = false, unique = true, updatable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "np_seq", insertable = false, unique = true, updatable = false)
    private ProjectEntity projectEntity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "nb_seq", insertable = false, unique = true, updatable = false)
    private BacklogEntity backlogEntity;
}