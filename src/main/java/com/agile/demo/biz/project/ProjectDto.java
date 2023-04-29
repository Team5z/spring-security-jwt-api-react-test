package com.agile.demo.biz.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private long np_seq;

    private String project_title;

    private String project_assign;

    @Override
    public String toString() {
        return "ProjectDto{" +
                "np_seq=" + np_seq +
                ", project_title='" + project_title + '\'' +
                ", project_assign='" + project_assign + '\'' +
                '}';
    }
}
