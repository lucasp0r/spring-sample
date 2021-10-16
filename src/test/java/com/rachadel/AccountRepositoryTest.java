package com.rachadel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rachadel.domain.Account;
import com.rachadel.repository.AccountRepository;

/**
 * @author Manoel Rachadel Neto
 * @since  15 de out. de 2021
 */
//@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {
	@Autowired
	private AccountRepository accountRespository;
	
	
	
	@Test
	public void saveShouldPersistData() {
		Account account = new Account(null, "Manoel Rachadel Neto", "manoelch13@gmail.com", "256.346.494-39");
		account = this.accountRespository.save(account);
		
		assertThat(account.getId()).isNotNull();
		assertThat(account.getName()).isEqualTo("Manoel Rachadel Neto");
		assertThat(account.getEmail()).isEqualTo("manoelch13@gmail.com");
		assertThat(account.getDocumentNumber()).isEqualTo("256.346.494-39");
	}
	
	@Test
	public void saveWhenDocumentNumberInvalidShouldThrowConstraintViolationException() {
		Exception exception = assertThrows(ConstraintViolationException.class, 
				() -> this.accountRespository.save(new Account(null,"João da Esquina","joao@gmail.com","123.456.789.22")));
		
		assertTrue(exception.getMessage().contains("número do registro de contribuinte individual brasileiro (CPF) inválido"));
	}
	
	@Test
	public void saveWhenEmailInvalidShouldThrowContraintViolationException() {
		Exception exception = assertThrows(ConstraintViolationException.class, 
				() -> this.accountRespository.save(new Account(null,"Vanessa da Silva", "sem e-mail", "187.068.858-96")));
		
		assertTrue(exception.getMessage().contains("Validation failed for classes [com.rachadel.domain.Account] during persist time for groups"));
	}
	
	@Test
	public void saveWhenNameIsBlankShouldThrowContraintViolationException() {
		Exception exception = assertThrows(ConstraintViolationException.class, 
				() -> this.accountRespository.save(new Account(null,"", "semnome@gmail.com", "187.068.858-96")));
		
		assertTrue(exception.getMessage().contains("Validation failed for classes [com.rachadel.domain.Account] during persist time for groups [javax.validation.groups.Default, ]"));
	}
	
	
	@Test
	public void deleteShouldRemoveData() {
		Account account = new Account(null,"Manoel Rachadel Neto", "manoelch13@gmail.com", "589.843.455-04");
		account = this.accountRespository.save(account);
		this.accountRespository.delete(account);
		
		assertThat(this.accountRespository.findById(account.getId())).isEmpty();
	}
	
	@Test
	public void updateShouldChangeData() {
		Account account = new Account(null,"Manoel Rachadel Neto", "manoelch13@gmail.com", "322.626.475-34");
		account = this.accountRespository.save(account);
		
		Account account2 = new Account(); 
		account2.setId(account.getId());
		account2.setName("Manoel R. Neto");
		account2.setEmail("admin@rachadel.com.br");
		account2.setDocumentNumber("864.146.624-02");
		this.accountRespository.save(account2);
		
		account = this.accountRespository.findById(account.getId()).get();	
			
		assertThat(account.getName()).isEqualTo("Manoel R. Neto");
		assertThat(account.getEmail()).isEqualTo("admin@rachadel.com.br");
		assertThat(account.getDocumentNumber()).isEqualTo("864.146.624-02");		
	}
	
	@Test
	public void findByDocumentNumberShouldFindByDocument() {
		this.accountRespository.save(new Account(null, "Maria Joaquina", "maria.joaquina@gmail.com", "477.535.449-34"));
		this.accountRespository.save(new Account(null, "Manoel Rachadel Neto", "manoelch13@gmail.com", "080.381.469-05"));
		
		Account account = this.accountRespository.findByDocumentNumber("080.381.469-05").orElse(new Account());
		
		assertThat(account.getDocumentNumber()).isEqualTo("080.381.469-05");
	}
}