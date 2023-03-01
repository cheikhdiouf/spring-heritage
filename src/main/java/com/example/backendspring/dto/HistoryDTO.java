package com.example.backendspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDTO {
    private  Long id;
    private double balance;
    private  int page;
    private int size;
    private  int currentPage;
    private  int totalePage;
   private List<OperationAccountDTO>operationAccountDTOS;

}
