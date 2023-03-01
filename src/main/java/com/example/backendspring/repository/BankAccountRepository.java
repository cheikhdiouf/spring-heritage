package com.example.backendspring.repository;

import com.example.backendspring.entity.BankAccount;
import com.example.backendspring.entity.OperationAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

}
