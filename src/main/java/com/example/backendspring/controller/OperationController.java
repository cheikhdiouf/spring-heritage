package com.example.backendspring.controller;

import com.example.backendspring.dto.OperationAccountDTO;
import com.example.backendspring.service.BankAccountService;
import com.example.backendspring.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OperationController {

    private OperationService operationService;

    public OperationController( OperationService operationService) {

        this.operationService = operationService;
    }
@GetMapping("/accounts/operations/{id}")
    List<OperationAccountDTO> ListByOperationAccountDTOS(@PathVariable Long id){
  return operationService.ListByOperationAccountDTOS(id);
    }
}
