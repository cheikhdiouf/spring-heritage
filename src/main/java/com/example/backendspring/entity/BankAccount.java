package com.example.backendspring.entity;

import com.example.backendspring.emuns.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 5,discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BankAccount  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    private double  balance;
    private Date createDate;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
   @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OperationAccount>operations;


}
