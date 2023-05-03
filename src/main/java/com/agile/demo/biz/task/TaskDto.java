package com.agile.demo.biz.task;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.backlog.BacklogEntity;
import com.agile.demo.biz.project.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
public class TaskDto {

    private Long nt_seq;

    private String title;

    private Integer storyProgress;

    private String description;

    private LocalDate deadline;

    private Long presenter;

    private Long manager;

    private AccountEntity account;

    private Long projectSeq;

    private Long backlogSeq;

    public void setDeadline(String deadline) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            // 예외 처리 코드 추가
            System.out.println("잘못된 날짜 형식입니다.");
            e.printStackTrace();
        }
    }

}