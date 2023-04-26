package com.agile.demo.biz.accountproject;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import com.agile.demo.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "NAccount_project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(AccountProjectKey.class)
public class AccountProjectEntity implements Serializable { // 복합키라서 implements Serializable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nap_seq", nullable = false, length = 25, unique = true)
    private Long nap_seq;

    @Id
    @Column(name="userId", nullable = false)
    private String userId;

    @Id
    @Column(name="np_seq", nullable = false)
    private Long np_seq;


    @OneToOne
    @MapsId
    @JoinColumn (name="user_id", referencedColumnName="user_id") // 삭제하면 같이 삭제되는 부분추가
    private AccountEntity accounts;

    @OneToOne
    @MapsId
    @JoinColumn (name="np_seq", referencedColumnName="np_seq")  // 삭제하면 같이 삭제되는 부분추가
    private ProjectEntity projectS;
}
