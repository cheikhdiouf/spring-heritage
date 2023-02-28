package com.example.backendspring.mappers;

import com.example.backendspring.controller.CustomerController;
import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//les autres methodes mapStruct
@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer ){
        CustomerDTO  customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        //customerDTO.setName(customer.getName());
       // customerDTO.setId(customer.getId());
        //customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO ){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
}
