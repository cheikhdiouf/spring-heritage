package com.example.backendspring.entity;

import com.example.backendspring.emuns.OperationType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperationAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation;
    private double amount ;
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
   private BankAccount bankAccount;
}
