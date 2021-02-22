package com.elizamamelo.produtctmanagment.module.autentication.service;

import com.elizamamelo.produtctmanagment.config.DomainUserDetailsService;
import com.elizamamelo.produtctmanagment.config.TokenGenerator;
import com.elizamamelo.produtctmanagment.module.autentication.service.dto.AuthResponse;
import com.elizamamelo.produtctmanagment.module.autentication.service.dto.request.AuthRequest;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.module.userAccount.mapper.UserAccountMapper;
import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import com.elizamamelo.produtctmanagment.module.userAccount.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;


@Service
public class AuthenticationService {


    private final AuthenticationManagerBuilder auth;
    private final DomainUserDetailsService userDetailsService;
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Autowired
    public AuthenticationService(AuthenticationManagerBuilder auth,
                                 DomainUserDetailsService userDetailsService,
                                 UserAccountRepository userAccountRepository,
                                 UserAccountMapper userAccountMapper) {
        this.auth = auth;
        this.userDetailsService = userDetailsService;
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
    }

    public AuthResponse authenticate(AuthRequest authRequest, HttpServletRequest request) {
        UserAccount user = userAccountRepository.findOneByUsernameIgnoreCase(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if (!bCrypt.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Incorrect password!");
        }

        UsernamePasswordAuthenticationToken authentication = setUserInContext(user.getUsername(), request);

        if (authentication.isAuthenticated()) {
            UserAccountResponse userResponse = userAccountMapper.toResponse(user);
            String token = new TokenGenerator().generateToken(userResponse);
            return new AuthResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "An error occurred while trying to authenticate.");
        }
    }

    private UsernamePasswordAuthenticationToken setUserInContext(String username, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
