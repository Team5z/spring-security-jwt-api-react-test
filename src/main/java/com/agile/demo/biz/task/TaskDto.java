package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TaskDto {

    private Long nt_seq;

    private String title;

    private int story_progress;

    private String description;

    private LocalDateTime create_date;

    private LocalDateTime update_date;

    private LocalDate deadline;

    private Long presenter;

    private Long manager;

    private AccountEntity account;

    private ProjectEntity projectEntity;

    private BacklogEntity backlogEntity;

    public void setDeadline(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.deadline = LocalDate.parse(deadline, formatter);
    }

}