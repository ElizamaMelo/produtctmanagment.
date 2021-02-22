package com.elizamamelo.produtctmanagment.module.userAccount.dto.response;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {

    private Long id;
    private String name;
    private String username;
}
