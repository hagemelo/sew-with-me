package br.com.jhage.sew_with_me.domain_service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhage.sew_with_me.domain_service.domain.NewOrderUseCase;
import br.com.jhage.sew_with_me.domain_service.exception.OrderException;
import br.com.jhage.sew_with_me.domain_service.model.Client;
import br.com.jhage.sew_with_me.domain_service.model.Order;
import br.com.jhage.sew_with_me.domain_service.repository.ClientRepository;
import br.com.jhage.sew_with_me.domain_service.repository.OrderRepository;

/***
 * 
 * @author Alexsander Melo
 * @since 31/03/2024
 *
 */

@Component
public class NewOrderUseCaseImpl implements NewOrderUseCase{
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	NewOrderUseCaseImpl(){}
	
	NewOrderUseCaseImpl(OrderRepository repository, ClientRepository clientRepository){
		
		this.repository = repository;
		this.clientRepository = clientRepository;
	}
	
	private Client prepareClient(final Client client) {
		
		Client prepareClient =  clientRepository.loadByName(client.getName());
		if(prepareClient==null) {
			prepareClient = clientRepository.save(client);
		}
		return prepareClient;
	}

	@Override
	public Order execute(Order order) throws OrderException {

		order.setNotNull();
		if (order.getClient() == null) {
			throw new OrderException("Order without client");
		}
		if (order.getSews() == null || order.getSews().isEmpty()) {
			throw new OrderException("Order without sew");
		}
		Order result= null;
		try {
			Client client = prepareClient(order.getClient());
			order = order.addClient(client);
			order.addSews(order.getSews());
			result = repository.save(order);
		} catch (Exception e) {
			throw new OrderException();
		}
		
		return result;
	}

}
