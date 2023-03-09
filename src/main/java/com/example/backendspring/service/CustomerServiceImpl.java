package com.example.backendspring.service;


import com.example.backendspring.dto.CustomerDTO;

import com.example.backendspring.entity.*;
import com.example.backendspring.exception.CustomerNotFoundException;
import com.example.backendspring.mappers.BankAccountMapperImpl;
import com.example.backendspring.repository.BankAccountRepository;
import com.example.backendspring.repository.CustomerRepository;
import com.example.backendspring.repository.OperationAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j //pour log les messsage
public class CustomerServiceImpl implements  CustomerService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapperImpl bankAccountMapperImpl;
    private CustomerRepository customerRepository;
    private OperationAccountRepository operationAccountRepository;

    public CustomerServiceImpl(BankAccountRepository bankAccountRepository,
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
    public List<CustomerDTO> listCustomer() {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDTO> customerDTOs =  customers.stream()
                .map(cust -> bankAccountMapperImpl.fromCustomer(cust)).
                collect(Collectors.toList());
         return   customerDTOs;
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

    @Override
    public List<CustomerDTO> seachCustomer(String keyword) {
        //List<Customer> customers=customerRepository.findByNameContains(keyword); // methode 1
        List<Customer> customers=customerRepository.SearchCustomer(keyword);
        List<CustomerDTO> customerDTOLis=customers.stream().map(op->bankAccountMapperImpl.fromCustomer(op)).collect(Collectors.toList());
        return  customerDTOLis;
    }
}
