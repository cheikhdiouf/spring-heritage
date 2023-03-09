package com.example.backendspring.mappers;

import com.example.backendspring.controller.CustomerController;
import com.example.backendspring.dto.*;
import com.example.backendspring.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//les autres methodes mapStruct ,modelMapper
@Service
public class BankAccountMapperImpl {
    //conversion customer en customerDTO
    public CustomerDTO fromCustomer(Customer customer ){
        CustomerDTO  customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        //customerDTO.setName(customer.getName());
       // customerDTO.setId(customer.getId());
        //customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
    //conversion customer en customerDTO
    public Customer fromCustomerDTO(CustomerDTO customerDTO ){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }


    public SavingAccountDTO  fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDTO savingAccountDTO=new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingAccountDTO);
        savingAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;

    }
    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }
    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDTO  currentAccountDTO=new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentAccountDTO);
        currentAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return  currentAccountDTO;
    }
    public  CurrentAccount fromCurrentAccountDTO( CurrentAccountDTO currentAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomerDTO()));
        return  currentAccount;
    }

    public OperationAccountDTO fromOperationAccount(OperationAccount operationAccount){
        OperationAccountDTO operationAccountDTO=new OperationAccountDTO();
        BeanUtils.copyProperties(operationAccount,operationAccountDTO);
        return  operationAccountDTO;
    }
    public  OperationAccount fromOperationAccountDTO(OperationAccountDTO operationAccountDTO){
        OperationAccount operationAccount=new OperationAccount();
        BeanUtils.copyProperties(operationAccountDTO,operationAccount);
        return  operationAccount;
    }
    public BankAccountDTO fromBankAccount(BankAccount bankAccount){
        BankAccountDTO bankAccountDTO=new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
        return  bankAccountDTO;
    }

 /*   public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO){
        BankAccount bankAccount=new BankAccount();
        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
        return  bankAccount;
    }*/
}
