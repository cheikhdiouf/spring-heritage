package com.example.backendspring.controller;

import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.exception.CustomerNotFoundException;
import com.example.backendspring.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerController {
    private BankAccountService bankAccountService;

    public CustomerController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/customer")
 public    List<CustomerDTO> ListCustomers(){
       return bankAccountService.listCustomer();
    }
    @GetMapping("/customer/{id}")
    public  CustomerDTO getCustomer( @PathVariable("id") Long customerId) throws CustomerNotFoundException {
       return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customer")

    public  CustomerDTO savedCustomer(@RequestBody CustomerDTO customerDTO){
        return  bankAccountService.saveCustomer(customerDTO);
    }
    /*@PutMapping("/customer/{customerId}")
    public  CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
          customerDTO.setId(customerId);
          return bankAccountService.updateCustomer(customerDTO);

    }*/
    @PutMapping("/customer/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
 @DeleteMapping("/customer/{id}")
    public  void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
 }

}
