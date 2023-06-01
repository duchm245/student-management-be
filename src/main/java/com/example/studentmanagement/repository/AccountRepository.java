package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String username);
}
