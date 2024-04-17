package br.com.jhage.sew_with_me.domain_service.application;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.jhage.sew_with_me.domain_service.exception.OrderException;
import br.com.jhage.sew_with_me.domain_service.model.Client;
import br.com.jhage.sew_with_me.domain_service.model.Order;
import br.com.jhage.sew_with_me.domain_service.model.Sew;
import br.com.jhage.sew_with_me.domain_service.model.TypeSew;
import br.com.jhage.sew_with_me.domain_service.repository.ClientRepository;
import br.com.jhage.sew_with_me.domain_service.repository.OrderRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class NewOrderUseCaseImplTest {
	
	
	@Mock
	OrderRepository orderRepositoryMock;
	
	@Mock
	ClientRepository clientRepositoryMock;
	
	
	@Test(expected = OrderException.class)
	public void deveLancarExceptionPorFaltaDeCliente() throws OrderException {
		
		Order order = new Order(new Date());
		Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
		
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(orderRepositoryMock, clientRepositoryMock);
		
		useCase.execute(order);
	}
	
	@Test(expected = OrderException.class)
	public void deveLancarExceptionPorFaltaDeCustura() throws OrderException {
		
		Client client = new Client("Fernando", "teste89898");
		Order order = new Order(new Date());
		order = order.addClient(client);
		Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(orderRepositoryMock, clientRepositoryMock);
		useCase.execute(order);
	}
	
	@Test
	public void deveSalvarNewOrderWithNewClient() throws OrderException {
		
		final Double VALOR = 23.;
		Client client = new Client("Fernando", "teste89898");
		Sew sew = new Sew(TypeSew.NOVO, new Date(),  VALOR);
		Order order = new Order(new Date());
		order = order.addClient(client).addSew(sew);
		
		
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(orderRepositoryMock, clientRepositoryMock);
		Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
		Mockito.when(clientRepositoryMock.loadByName(client.getName())).thenReturn(null);
		order = useCase.execute(order);
		
		assertTrue(order!=null);
		assertTrue(order.getSews()!=null);
		assertTrue(order.getStatus()!=null);
		assertTrue(order.getValue()!=null);
		assertTrue(order.getDeliveryForecast()!=null);
		assertTrue(order.getCreated_at()!=null);
		assertTrue(order.getClient()!=null);
		assertTrue(!order.getSews().isEmpty());
		assertTrue(Double.compare(order.getValue(), VALOR) == 0);
	}
	
	@Test
	public void deveSalvarNewOrderWithClient() throws OrderException {
		
		final Double VALOR = 23.;
		Client client = new Client("Fernando", "teste89898");
		Sew sew = new Sew(TypeSew.NOVO, new Date(),  VALOR);
		Order order = new Order(new Date());
		order = order.addClient(client).addSew(sew);
		
		
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(orderRepositoryMock, clientRepositoryMock);
		Mockito.when(orderRepositoryMock.save(order)).thenReturn(order);
		Mockito.when(clientRepositoryMock.loadByName(client.getName())).thenReturn(client);
		order = useCase.execute(order);
		
		assertTrue(order!=null);
		assertTrue(order.getSews()!=null);
		assertTrue(order.getStatus()!=null);
		assertTrue(order.getValue()!=null);
		assertTrue(order.getDeliveryForecast()!=null);
		assertTrue(order.getCreated_at()!=null);
		assertTrue(order.getClient()!=null);
		assertTrue(!order.getSews().isEmpty());
		assertTrue(Double.compare(order.getValue(), VALOR) == 0);
	}
	
}
