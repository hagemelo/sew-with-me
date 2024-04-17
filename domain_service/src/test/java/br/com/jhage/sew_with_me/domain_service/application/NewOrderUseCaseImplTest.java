package br.com.jhage.sew_with_me.domain_service.application;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import br.com.jhage.sew_with_me.domain_service.exception.OrderException;
import br.com.jhage.sew_with_me.domain_service.model.Client;
import br.com.jhage.sew_with_me.domain_service.model.Order;
import br.com.jhage.sew_with_me.domain_service.model.Sew;
import br.com.jhage.sew_with_me.domain_service.model.TypeSew;
import br.com.jhage.sew_with_me.domain_service.repository.OrderRepository;

public class NewOrderUseCaseImplTest {
	
	
	@Test(expected = OrderException.class)
	public void deveLancarExceptionPorFaltaDeCliente() throws OrderException {
		
		OrderRepositoryMock repositoryMock = new OrderRepositoryMock();
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(repositoryMock);
		Order order = new Order(new Date());
		useCase.execute(order);
	}
	
	@Test(expected = OrderException.class)
	public void deveLancarExceptionPorFaltaDeCustura() throws OrderException {
		
		OrderRepositoryMock repositoryMock = new OrderRepositoryMock();
		Client client = new Client("Fernando", "teste89898");
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(repositoryMock);
		Order order = new Order(new Date());
		order = order.addClient(client);
		useCase.execute(order);
	}
	
	@Test
	public void deveSalvarNewOrder() throws OrderException {
		
		final Double VALOR = 23.;
		
		OrderRepositoryMock repositoryMock = new OrderRepositoryMock();
		Client client = new Client("Fernando", "teste89898");
		Sew sew = new Sew(TypeSew.NOVO, new Date(),  VALOR);
		NewOrderUseCaseImpl useCase = new NewOrderUseCaseImpl(repositoryMock);
		Order order = new Order(new Date());
		order = order.addClient(client).addSew(sew);
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
	
	
	
	class OrderRepositoryMock implements OrderRepository{

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <S extends Order> S saveAndFlush(S entity) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void deleteAllInBatch(Iterable<Order> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllByIdInBatch(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllInBatch() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Order getOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Order getById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Order getReferenceById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> List<S> findAll(Example<S> example) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> List<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Order> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Order> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> S save(S entity) {
			
			return entity;
		}

		@Override
		public Optional<Order> findById(Long id) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Order entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllById(Iterable<? extends Long> ids) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll(Iterable<? extends Order> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Order> findAll(Sort sort) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<Order> findAll(Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> Optional<S> findOne(Example<S> example) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <S extends Order> long count(Example<S> example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public <S extends Order> boolean exists(Example<S> example) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public <S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}

}
