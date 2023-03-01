package com.example.backendspring.dto;

import com.example.backendspring.emuns.OperationType;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
public class OperationAccountDTO {
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOperation;
    private double amount ;
    private String description;
    private OperationType operationType;

}
