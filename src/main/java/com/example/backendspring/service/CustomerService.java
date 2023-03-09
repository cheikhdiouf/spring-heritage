package com.example.backendspring.service;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.dto.CurrentAccountDTO;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.dto.SavingAccountDTO;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
     List<CustomerDTO> listCustomer();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    List<CustomerDTO> seachCustomer(String keyword);
}
