package br.com.jhage.sew_with_me.domain_service.model;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;

public class OrderTest {
	
	
	@Test
	public void deveTransformarObjetoEmJson() throws JsonProcessingException {
		
		Order order = new Order(new Date());
		final String orderJson = new ObjectMapper().writeValueAsString(order);
		assertTrue(orderJson!=null);
		assertTrue(orderJson.length()>0);
	}
	
	@Test
	public void deveTerOsPrincipaisAtributosDiferentesDeNull() {
		
		Order order = new Order(new Date());
		assertTrue(order.getSews()!=null);
		assertTrue(order.getStatus()!=null);
		assertTrue(order.getValue()!=null);
		assertTrue(order.getDeliveryForecast()!=null);
		assertTrue(order.getCreated_at()!=null);
	}
	
	@Test
	public void deveTerOsPrincipaisAtributosDiferentesDeNullApartirJson() throws JsonMappingException, JsonProcessingException {
		
		String json = "{\"value\":23.0,\"deliveryForecast\":\"16/04/2024\"}";
		
		Order order = new ObjectMapper().readValue(json, Order.class);
		assertTrue(order.getSews()!=null);
		assertTrue(order.getStatus()!=null);
		assertTrue(order.getValue()!=null);
		assertTrue(order.getDeliveryForecast()!=null);
		assertTrue(order.getCreated_at()!=null);
		assertTrue(order.getSews().isEmpty());
		assertTrue(order.getValue()!= ValoresConstante.DOUBLE_ZERO );
		assertTrue(order.getStatus()==OrderStatus.ABERTO);
	}
	
	
}
