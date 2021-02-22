package com.elizamamelo.produtctmanagment.module.userAccount.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class UserAccountRequest {

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "Incorrect username format")
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}

