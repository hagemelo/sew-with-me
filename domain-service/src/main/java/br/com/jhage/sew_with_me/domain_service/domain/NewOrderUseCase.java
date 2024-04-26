package br.com.jhage.sew_with_me.domain_service.domain;

import br.com.jhage.sew_with_me.domain_service.exception.OrderException;
import br.com.jhage.sew_with_me.domain_service.model.Order;

public interface NewOrderUseCase {
	
	public Order execute(Order order) throws OrderException;

}
