package br.com.jhage.sew_with_me.domain_service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.jhage.sew_with_me.domain_service.domain.OrderMapper;
import br.com.jhage.sew_with_me.domain_service.model.Order;


/**
 * 
 * @author Alexsander Melo
 * @since 04/04/2018
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DomainApplication.class)
public class OrderMapperTest {
	
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void quandoUmJsonCorretoEUtilizadoDeveRetornarUmaInstanciaDeOrder(){
		
		String jsonString = "{\"created_at\":\"04/04/2024\",\"value\":30,}";
		
		Order order = orderMapper.convert(jsonString);
		
		assertTrue(order!=null);
	}
	

}
