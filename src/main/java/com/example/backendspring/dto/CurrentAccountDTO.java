package com.example.backendspring.dto;

import com.example.backendspring.emuns.AccountStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public  class CurrentAccountDTO extends  BankAccountDTO {
     private Long id;
    private double  balance;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String currency;
    private AccountStatus accountStatus;
    private CustomerDTO customerDTO;
    private double overDraft;

}
