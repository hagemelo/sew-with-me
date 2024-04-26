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

	@Override
	public Order execute(Order order) throws OrderException {
		
		order.setNotNull();
		if (order.getClient()==null) {
			throw new OrderException("Order without client");
		}
		if (order.getSews()==null || order.getSews().isEmpty()) {
			throw new OrderException("Order without sew");
		}
		// Verificar se o Cliente Ja existe
		Client client = clientRepository.loadByName(order.getClient().getName());
		order = order.addClient(client!=null? client: order.getClient());
		
		
		
		Order result = repository.save(order);
		return result;
	}

}
