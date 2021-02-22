package com.elizamamelo.produtctmanagment.rest.api;


import com.elizamamelo.produtctmanagment.module.userAccount.dto.request.UserAccountRequest;
import com.elizamamelo.produtctmanagment.module.userAccount.dto.response.UserAccountResponse;
import com.elizamamelo.produtctmanagment.module.userAccount.service.UserAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserAccountRest {

    @Autowired
    private UserAccountServices service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccountResponse create(@Valid @RequestBody UserAccountRequest request) throws Exception {
        return service.create(request);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountResponse findById(@RequestBody @PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountResponse> findAll() throws Exception {
        return service.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountResponse update(@PathVariable Long id, @RequestBody UserAccountRequest request) throws Exception {
        return service.update(id, request);
    }

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws Exception {
        service.deleteById(id);
    }

}
