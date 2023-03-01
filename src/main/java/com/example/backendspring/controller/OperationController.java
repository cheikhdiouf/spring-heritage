package com.example.backendspring.controller;

import com.example.backendspring.dto.HistoryDTO;
import com.example.backendspring.dto.OperationAccountDTO;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.service.BankAccountService;
import com.example.backendspring.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OperationController {

    private OperationService operationService;

    public OperationController( OperationService operationService) {

        this.operationService = operationService;
    }
    //list operation by id compte
@GetMapping("/accounts/operations/{id}")
    List<OperationAccountDTO> ListByOperationAccountDTOS(@PathVariable Long id){
  return operationService.ListByOperationAccountDTOS(id);
    }

    @GetMapping("/accounts/history/{id}")
   public HistoryDTO getHistory(
            @PathVariable Long id,
            @RequestParam(name = "page",defaultValue ="0" )int page ,
            @RequestParam(name="size",defaultValue ="5" )int size
       ) throws BankAccountNotFoundException {

        return operationService.getAccountHistory(id,page,size);
    }



}
