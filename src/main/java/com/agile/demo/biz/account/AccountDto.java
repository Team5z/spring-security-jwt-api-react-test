package com.agile.demo.biz.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AccountDto {
    private String userId;
    private String password;

    private String role;

    private String name;
    private String phone;
    private String email;
}
