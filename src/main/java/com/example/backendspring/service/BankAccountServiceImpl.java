package com.example.backendspring.service;

import com.example.backendspring.emuns.OperationType;
import com.example.backendspring.entity.BankAccount;
import com.example.backendspring.entity.CurrentAccount;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.entity.OperationAccount;
import com.example.backendspring.exception.BalanceNotFoundException;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;
import com.example.backendspring.repository.BankAccountRepository;
import com.example.backendspring.repository.CustomerRepository;
import com.example.backendspring.repository.OperationAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
@Slf4j //pour log les messsage
public class BankAccountServiceImpl implements  BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private OperationAccountRepository operationAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  CustomerRepository customerRepository,
                                  OperationAccountRepository operationAccountRepository) {

        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.operationAccountRepository = operationAccountRepository;
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("save customer");
        Customer savedCustomer =customerRepository.save(customer);
        return  savedCustomer;

    }

    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            throw  new CustomerNotFoundException("customer not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setCreateDate(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedCurrentAccount=bankAccountRepository.save(currentAccount);
        return savedCurrentAccount;
    }


    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }
    @Override
    public List<BankAccount> listBankAccount() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount saveSavingCurrentBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public BankAccount getBankAccount(Long accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).
                orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));

        return bankAccount;
    }

    @Override
    public void debite(Long accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotFoundException {
       BankAccount bankAccount=getBankAccount(accountId);
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
             BankAccount bankAccount=getBankAccount(accountId);
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
}
