package com.example.backendspring.controller;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {
private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
@GetMapping("/accounts/{id}")
   public BankAccountDTO getbankAccountDTO(@PathVariable Long id) throws BankAccountNotFoundException {
        return  bankAccountService.getBankAccount(id);
   }
 @GetMapping("/accounts")
    public List<BankAccountDTO>ListAccountDTO(){
        return  bankAccountService.listBankAccount();
 }


    @DeleteMapping("/accounts/{id}")
    public  void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteBanKAccount(id);
    }

 /*   @PutMapping("/account/{id}")
    public BankAccountDTO updateBankAccount(@PathVariable Long id, @RequestBody BankAccountDTO bankAccountDTO){
        bankAccountDTO.setId(id);
        return bankAccountService.updateBanKAccount(bankAccountDTO);
    }
*/
}
