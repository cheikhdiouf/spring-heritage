package com.example.backendspring.service;

import com.example.backendspring.dto.BankAccountDTO;
import com.example.backendspring.dto.CurrentAccountDTO;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.dto.SavingAccountDTO;
import com.example.backendspring.emuns.OperationType;
import com.example.backendspring.entity.*;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;
import com.example.backendspring.mappers.BankAccountMapperImpl;
import com.example.backendspring.repository.BankAccountRepository;
import com.example.backendspring.repository.CustomerRepository;
import com.example.backendspring.repository.OperationAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j //pour log les messsage
public class BankAccountServiceImpl implements  BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapperImpl bankAccountMapperImpl;
    private CustomerRepository customerRepository;
    private OperationAccountRepository operationAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  BankAccountMapperImpl bankAccountMapperImpl, CustomerRepository customerRepository,
                                  OperationAccountRepository operationAccountRepository) {

        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapperImpl = bankAccountMapperImpl;
        this.customerRepository = customerRepository;
        this.operationAccountRepository = operationAccountRepository;
    }


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer=bankAccountMapperImpl.fromCustomerDTO(customerDTO);
        Customer savedCustomer=customerRepository.save(customer);
        return bankAccountMapperImpl.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw  new CustomerNotFoundException("customer not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setCreateDate(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedCurrentAccount=bankAccountRepository.save(currentAccount);
        return bankAccountMapperImpl.fromCurrentAccount(savedCurrentAccount);
    }


    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDTO> customerDTOs =  customers.stream()
                .map(cust -> bankAccountMapperImpl.fromCustomer(cust)).
                collect(Collectors.toList());
         return   customerDTOs;
    }
    @Override
    public List<BankAccountDTO> listBankAccount() {
       List<BankAccount> bankAccounts=bankAccountRepository.findAll();
       List<BankAccountDTO> bankAccountDTOS =bankAccounts.stream().map(bankAccount -> {
           if (bankAccount instanceof SavingAccount){
               SavingAccount savingAccount=(SavingAccount) bankAccount;
               return  bankAccountMapperImpl.fromSavingAccount(savingAccount);
           }else {
               CurrentAccount currentAccount=(CurrentAccount) bankAccount;
               return  bankAccountMapperImpl.fromCurrentAccount(currentAccount);
           }
       }).collect(Collectors.toList());
        return bankAccountDTOS ;
    }

    @Override
    public SavingAccountDTO saveSavingCurrentBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw  new CustomerNotFoundException("customer not found");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setCreateDate(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedSavingAccount=bankAccountRepository.save( savingAccount);
        return bankAccountMapperImpl.fromSavingAccount(savedSavingAccount);
    }

    @Override
    public BankAccountDTO getBankAccount(Long id) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(id).
                orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
  if(bankAccount instanceof SavingAccount )
{
    SavingAccount savingAccount=(SavingAccount) bankAccount;
    return  bankAccountMapperImpl.fromSavingAccount(savingAccount);

} else  {

      CurrentAccount currentAccount=(CurrentAccount) bankAccount;
      return  bankAccountMapperImpl.fromCurrentAccount(currentAccount);
  }
       // return bankAccount;
    }

    @Override
    public void debite(Long accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).
                orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
       if(bankAccount.getBalance() < amount)
           throw  new BalanceNotFoundException("solde est insuffisant");
        OperationAccount operationAccount=new OperationAccount();
        operationAccount.setAmount(amount);
        operationAccount.setDateOperation(new Date());
        operationAccount.setDescription(description);
        operationAccount.setOperationType(OperationType.DEBIT);
        operationAccount.setBankAccount(bankAccount);
        operationAccountRepository.save(operationAccount);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credite(Long accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).
                orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
             OperationAccount operationAccount=new OperationAccount();
             operationAccount.setOperationType(OperationType.CREDIT);
             operationAccount.setAmount(amount);
             operationAccount.setDescription(description);
             operationAccount.setDateOperation(new Date());
             operationAccount.setBankAccount(bankAccount);
             bankAccount.setBalance(bankAccount.getBalance()+amount);
             bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfert(Long accountIdSource, double amount, Long accountIdDestination) {

    }
    @Override
    public  CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
     Customer customer =customerRepository.findById(customerId).
                 orElseThrow(() ->new CustomerNotFoundException("not found"));
         return bankAccountMapperImpl.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer=bankAccountMapperImpl.fromCustomerDTO(customerDTO);
        Customer savedCustomer=customerRepository.save(customer);
        return bankAccountMapperImpl.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
