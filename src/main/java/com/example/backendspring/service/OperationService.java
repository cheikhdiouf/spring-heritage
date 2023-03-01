package com.example.backendspring.service;

import com.example.backendspring.dto.*;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OperationService {

    List<OperationAccountDTO>ListByOperationAccountDTOS(Long id);


    HistoryDTO getAccountHistory(Long id, int page, int size) throws BankAccountNotFoundException;
}
