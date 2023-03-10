package com.example.backendspring.mappers;

import com.example.backendspring.controller.CustomerController;
import com.example.backendspring.dto.CurrentAccountDTO;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.dto.SavingAccountDTO;
import com.example.backendspring.entity.CurrentAccount;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.entity.SavingAccount;
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
}
