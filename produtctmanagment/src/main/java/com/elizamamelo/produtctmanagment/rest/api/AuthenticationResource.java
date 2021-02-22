package com.elizamamelo.produtctmanagment.rest.api;

import com.elizamamelo.produtctmanagment.module.autentication.service.AuthenticationService;
import com.elizamamelo.produtctmanagment.module.autentication.service.dto.AuthResponse;
import com.elizamamelo.produtctmanagment.module.autentication.service.dto.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthenticationResource {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationResource(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(value = "/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest credentialRequest, HttpServletRequest request) {
        return service.authenticate(credentialRequest, request);
    }
}
