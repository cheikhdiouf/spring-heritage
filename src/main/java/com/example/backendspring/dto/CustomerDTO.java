package com.example.backendspring.dto;

import lombok.Data;
;

import javax.persistence.*;


@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
