package com.example.backendspring.service;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.dto.CurrentAccountDTO;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.dto.SavingAccountDTO;
import com.example.backendspring.entity.BankAccount;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentAccountDTO saveCurrentBankAccount(double initialBalance,double overDraft,Long customerId) throws CustomerNotFoundException;
     List<CustomerDTO> listCustomer();

    List<BankAccountDTO> listBankAccount();

    SavingAccountDTO saveSavingCurrentBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     BankAccountDTO getBankAccount(Long id) throws BankAccountNotFoundException;
     void debite(Long accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotFoundException;
    void credite(Long accountId,double amount,String description) throws BankAccountNotFoundException;
    void transfert (Long accountIdSource,double amount,Long accountIdDestination);

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
}
