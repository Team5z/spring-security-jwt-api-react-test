package com.agile.demo.biz.project.account;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Setter
@Getter
public class AccountProjectDto implements Serializable {

    private String accountUserId;

    private Long projectSeq;
}
