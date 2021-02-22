package com.elizamamelo.produtctmanagment.module.userAccount.repository;

import com.elizamamelo.produtctmanagment.module.userAccount.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByUsername(String username);

    Optional<UserAccount> findOneByNameIgnoreCase(String name);

    Optional<UserAccount> findOneByUsernameIgnoreCase(String username);


}