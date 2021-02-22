package com.elizamamelo.produtctmanagment.module.userAccount.mapper;


import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.module.userAccount.model.Role;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAccountMapper {


    public UserAccountResponse toResponse(UserAccount userAccount) {
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .name(userAccount.getName())
                .username(userAccount.getUsername())
                .build();
    }

    public List<UserAccountResponse> toList(List<UserAccount> objects) {
        List<UserAccountResponse> userAccounts = new ArrayList<>();

        objects.forEach(obj -> {
            userAccounts.add(this.toResponse(obj));
        });
        return userAccounts;
    }

    private Set<String> getRoles(UserAccount userAccount) {
        return userAccount.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }


}
