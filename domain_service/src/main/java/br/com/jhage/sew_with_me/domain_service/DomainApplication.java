package br.com.jhage.sew_with_me.domain_service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Alexsander Melo
 * @since 30/03/2024
 *
 */
@SpringBootApplication
public class DomainApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(DomainApplication.class, args).close();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		System.in.read();
	}
}
