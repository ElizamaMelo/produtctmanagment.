package com.elizamamelo.produtctmanagment.module.userAccount.service;

import com.elizamamelo.produtctmanagment.exception.UserAccountException;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.request.UserAccountRequest;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.enums.Error;
import com.elizamamelo.produtctmanagment.module.userAccount.mapper.UserAccountMapper;
import com.elizamamelo.produtctmanagment.module.userAccount.model.Role;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import com.elizamamelo.produtctmanagment.module.userAccount.repository.RoleRepository;
import com.elizamamelo.produtctmanagment.module.userAccount.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserAccountServices {

    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAccountMapper mapper;

    public UserAccount getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return repository.findOneByNameIgnoreCase(currentPrincipalName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public UserAccountResponse create(UserAccountRequest request) throws Exception {
        try {
            this.existsByUsername(request.getUsername());
            UserAccount userAccount = UserAccount.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                    .build();

            UserAccount savedUser = repository.save(userAccount);
            Role role = Role.builder().name("ROLE_USER").userAccount(savedUser).build();
            roleRepository.saveAndFlush(role);
            return mapper.toResponse(savedUser);
        } catch (Exception e) {
            throw new Exception("Server error has ocurred", e);
        }

    }

    private void existsByUsername(String email) {
        boolean existEmail = repository.existsByUsername(email);
        if (existEmail) {
            throw new UserAccountException(Error.EMAIL_ALREADY_EXISTS.getError());
        }
    }

    public UserAccountResponse findById(Long id) throws Exception {
        try {
            UserAccount userAccount = repository.findById(id).get();
            return mapper.toResponse(userAccount);
        } catch (Exception e) {
            throw new UserAccountException(Error.USER_NOT_FOUND.getError());
        }
    }

    public List<UserAccountResponse> findAll() throws Exception {
        try {
            List<UserAccount> accounts = repository.findAll();
            return mapper.toList(accounts);
        } catch (Exception e) {
            throw new Exception("A server error has occurred.", e);
        }
    }


    public UserAccountResponse update(Long id, UserAccountRequest request) throws Exception {
        try {
            UserAccount userAccount = repository.findById(id).get();
            userAccount.setName(request.getName());
            userAccount.setUsername(request.getUsername());
            userAccount.setPassword(request.getPassword());
            UserAccount userAccountUpdated = repository.save(userAccount);
            return mapper.toResponse(userAccountUpdated);
        } catch (Exception e) {
            throw new Exception("A server error has occurred.", e);
        }
    }

    public void deleteById (Long id) throws Exception {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("A server error has occurred.", e);
        }

    }
}