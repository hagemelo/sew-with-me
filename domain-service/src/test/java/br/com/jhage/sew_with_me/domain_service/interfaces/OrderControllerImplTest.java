package br.com.jhage.sew_with_me.domain_service.interfaces;


import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import br.com.jhage.sew_with_me.domain_service.DomainApplication;

@Ignore
@SpringBootTest(classes=DomainApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerImplTest {
	
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void greetingShouldReturnDefaultMessage() throws Exception {
		String result = this.restTemplate.getForObject("http://localhost:" + port + "/order/newOrder",
				String.class);
		System.out.println(result);
	}

}
