package com.example.backendspring;

import com.example.backendspring.emuns.AccountStatus;
import com.example.backendspring.emuns.OperationType;
import com.example.backendspring.entity.*;
import com.example.backendspring.repository.BankAccountRepository;
import com.example.backendspring.repository.CustomerRepository;
import com.example.backendspring.repository.OperationAccountRepository;
import lombok.ToString;
import net.bytebuddy.utility.RandomString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository){
		return args -> {
			BankAccount bankAccount=bankAccountRepository.findById(1000L).orElse(null);
			if(bankAccount!=null){
			System.out.println(bankAccount.getId());
			System.out.println(bankAccount.getBalance());
			System.out.println(bankAccount.getAccountStatus());
			System.out.println(bankAccount.getCreateDate());
			System.out.println(bankAccount.getCustomer().getEmail());
			System.out.println(bankAccount.getCustomer().getName());
			System.out.println(bankAccount.getClass().getSimpleName());
			if(bankAccount instanceof  CurrentAccount)
			{
				System.out.println("c'est compte courant =>"+((CurrentAccount)bankAccount).getOverDraft());
			}
			else if(bankAccount instanceof  SavingAccount)
			{
				System.out.println("c'est compte economie =>"+((SavingAccount)bankAccount).getInterestRate());
			}

			bankAccount.getOperations().forEach(op->{
				System.out.println("###########################");
				System.out.println("###########################");
				System.out.println(op.getId());
				System.out.println(op.getDateOperation());
				System.out.println(op.getAmount());
				System.out.println(op.getOperationType());
				System.out.println("###########################");
				System.out.println("###########################");
			});
		}else{System.out.println("###########################");
				System.out.println("###########################");
				System.out.println("ce numero compte n'existe pas");
				System.out.println("###########################");
				System.out.println("###########################");
			}
		};
	}
@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							OperationAccountRepository operationAccountRepository){
	Random rnd=new Random();
		return args -> {
			Stream.of("cheikh","abdou","sokhna").forEach(name->{
				Customer customer=new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
customerRepository.findAll().forEach(cust ->{
	CurrentAccount currentAccount=new CurrentAccount();
	currentAccount.setId(100+rnd.nextLong());
	currentAccount.setAccountStatus(AccountStatus.ACTIVATED);
	currentAccount.setCreateDate(new Date());
	currentAccount.setOverDraft(900);
	currentAccount.setCurrency(RandomString.make(10));
	currentAccount.setCustomer(cust);
	bankAccountRepository.save(currentAccount);
} );

customerRepository.findAll().forEach(cust ->{
	SavingAccount savingAccount=new SavingAccount()	;
	savingAccount.setBalance(Math.random()*700);
	savingAccount.setAccountStatus(AccountStatus.CREATED);
	savingAccount.setCreateDate(new Date());
	savingAccount.setCurrency(RandomString.make(10));
	savingAccount.setInterestRate(1000000000);
	savingAccount.setCustomer(cust);
	bankAccountRepository.save(savingAccount);
} );
//enrgistre des operation dans un banque
bankAccountRepository.findAll().forEach(ban->{
	for (int i=0;i<10;i++){
		OperationAccount operationAccount=new OperationAccount();
		operationAccount.setDateOperation(new Date());
       operationAccount.setAmount(Math.random()*1000);
	   operationAccount.setDescription(RandomString.make(6));
	   operationAccount.setOperationType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
	   operationAccount.setBankAccount(ban);
	   operationAccountRepository.save(operationAccount);

	}

});



		};
}
}
