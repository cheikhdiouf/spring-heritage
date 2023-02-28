package com.example.backendspring.repository;

import com.example.backendspring.entity.OperationAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationAccountRepository extends JpaRepository<OperationAccount,Long> {
}
