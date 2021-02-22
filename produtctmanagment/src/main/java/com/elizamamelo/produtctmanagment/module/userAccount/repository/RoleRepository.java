package com.elizamamelo.produtctmanagment.module.userAccount.repository;

import com.elizamamelo.produtctmanagment.module.userAccount.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}