package com.example.backendspring.service;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.dto.CurrentAccountDTO;
import com.example.backendspring.dto.OperationAccountDTO;
import com.example.backendspring.dto.SavingAccountDTO;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;

import java.util.List;

public interface OperationService {

    List<OperationAccountDTO>ListByOperationAccountDTOS(Long id);
}
