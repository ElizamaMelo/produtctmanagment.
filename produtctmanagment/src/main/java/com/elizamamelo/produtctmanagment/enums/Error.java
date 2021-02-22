package com.elizamamelo.produtctmanagment.enums;

import lombok.Getter;

@Getter
public enum Error {

    EMAIL_ALREADY_EXISTS("Email already registered!"),

    USER_NOT_FOUND("No user was found!"),

    PRODUCT_NOT_FOUND("No product was found!");

    private String error;

    Error(String error) {
        this.error = error;
    }

}