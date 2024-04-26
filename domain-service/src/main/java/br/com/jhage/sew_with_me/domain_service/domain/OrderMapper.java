package br.com.jhage.sew_with_me.domain_service.domain;

import br.com.jhage.sew_with_me.domain_service.model.Order;

public interface OrderMapper {
	
	public Order convert(String source);
}
