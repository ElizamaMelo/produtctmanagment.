package com.elizamamelo.produtctmanagment.exception;

import lombok.Getter;

@Getter
public class UserAccountException extends RuntimeException {

    public UserAccountException(String message) {
        super(message);
    }
}
