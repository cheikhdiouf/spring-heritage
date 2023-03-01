package com.example.backendspring.dto;

import com.example.backendspring.emuns.AccountStatus;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.entity.OperationAccount;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public  class SavingAccountDTO extends BankAccountDTO {
     private Long id;
    private double  balance;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String currency;
    private AccountStatus accountStatus;
    private CustomerDTO customerDTO;
    private double interestRate;

}
