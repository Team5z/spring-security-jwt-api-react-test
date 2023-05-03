package com.agile.demo.biz.backlog;

import com.agile.demo.biz.project.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacklogDto {

    private Long nb_seq;

    private String title;

    private Integer storyProgress;

    private String description;

    private Long projectSeq;
}
