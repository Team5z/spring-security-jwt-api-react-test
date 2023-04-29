package com.agile.demo.biz.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class AccountDto {
    @Column(unique = true)
    private String userId;
    private String password;

    private String role;

    private String name;
    private String phone;
    private String email;
}
