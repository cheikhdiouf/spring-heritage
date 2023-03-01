package com.example.backendspring.service;

import com.example.backendspring.dto.CustomerDTO;
import com.example.backendspring.dto.HistoryDTO;
import com.example.backendspring.dto.OperationAccountDTO;
import com.example.backendspring.entity.BankAccount;
import com.example.backendspring.entity.Customer;
import com.example.backendspring.entity.OperationAccount;
import com.example.backendspring.exception.BankAccountNotFoundException;
import com.example.backendspring.exception.CustomerNotFoundException;
import com.example.backendspring.mappers.BankAccountMapperImpl;
import com.example.backendspring.repository.BankAccountRepository;
import com.example.backendspring.repository.CustomerRepository;
import com.example.backendspring.repository.OperationAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j //pour log les messsage
public class OperationServiceImpl implements  OperationService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapperImpl bankAccountMapperImpl;
    private CustomerRepository customerRepository;
    private OperationAccountRepository operationAccountRepository;

    public OperationServiceImpl(BankAccountRepository bankAccountRepository,
                                BankAccountMapperImpl bankAccountMapperImpl, CustomerRepository customerRepository,
                                OperationAccountRepository operationAccountRepository) {

        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapperImpl = bankAccountMapperImpl;
        this.customerRepository = customerRepository;
        this.operationAccountRepository = operationAccountRepository;
    }

    @Override
    public List<OperationAccountDTO> ListByOperationAccountDTOS(Long id) {
       List<OperationAccount>  operationAccounts=operationAccountRepository.findByBankAccountId(id);
    return   operationAccounts.stream().map(op->bankAccountMapperImpl.fromOperationAccount(op)).collect(Collectors.toList());
    }

    @Override
    public HistoryDTO getAccountHistory(Long id, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(id).orElse(null);
        if(bankAccount==null) throw  new BankAccountNotFoundException("not found");

       Page<OperationAccount> operationAccount=operationAccountRepository.findByBankAccountId(id,  PageRequest.of(page, size));
      HistoryDTO historyDTO=new HistoryDTO();
      List<OperationAccountDTO> operationAccountDTOS =operationAccount.getContent().stream().map(op->bankAccountMapperImpl.fromOperationAccount(op)).collect(Collectors.toList());
      historyDTO.setBalance(bankAccount.getBalance());
      historyDTO.setId(bankAccount.getId());
      historyDTO.setOperationAccountDTOS(operationAccountDTOS);
      historyDTO.setCurrentPage(page);
      historyDTO.setSize(size);
      historyDTO.setTotalePage(operationAccount.getTotalPages());
      return  historyDTO;
    }


}
