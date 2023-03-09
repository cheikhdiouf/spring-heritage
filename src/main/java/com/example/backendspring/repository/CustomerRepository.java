package com.example.backendspring.repository;

import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {
   // List<Customer> findByNameContains(String keyword);// methode 1 rechercher
    @Query("select c from Customer c where c.name like :kd ")
   List<Customer> SearchCustomer( @Param(value = "kd") String keyword); // methode 1 rechercher

}
