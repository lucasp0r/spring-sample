package com.rachadel;

import java.util.List;
import static java.util.Arrays.asList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.rachadel.domain.Account;
import com.rachadel.repository.AccountRepository;

/**
 * @author Manoel Rachadel Neto
 * @since  16 de out. de 2021
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@TestConfiguration
	static class Config{
		
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("teste", "teste");
		}
	}
	
	@Test
	public void listAccountsWhenCorrectShouldReturnStatusCode200() {
		List<Account> accounts = asList(new Account(1L, "Manoel Rachadel Neto", "manoelch13@gmail.com", "080.381.469-05"),
										new Account(1L, "Maria Joaquina", "maria.joaquina@gmail.com", "187.068.858-96"));
		BDDMockito.when(accountRepository.findAll()).thenReturn(accounts);
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/accounts", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void getAccountsByIdShouldReturnStatusCode200() {
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/accounts/1", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
}
