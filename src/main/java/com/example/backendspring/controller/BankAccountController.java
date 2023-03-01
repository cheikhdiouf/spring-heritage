package com.example.backendspring.controller;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.service.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {
private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
@GetMapping("/account/{id}")
   public BankAccountDTO getbankAccountDTO(@PathVariable Long id) throws BankAccountNotFoundException {
        return  bankAccountService.getBankAccount(id);
   }
 @GetMapping("/account")
    public List<BankAccountDTO>ListAccountDTO(){
        return  bankAccountService.listBankAccount();
 }
}
