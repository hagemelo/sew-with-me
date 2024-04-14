package br.com.jhage.sew_with_me.domain.domain;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jhage.sew_with_me.domain.exception.OrderException;
import br.com.jhage.sew_with_me.domain.model.Order;

public interface OrderController {
	
	public @ResponseBody Order newOrder(@RequestBody Order order) throws OrderException;

}
