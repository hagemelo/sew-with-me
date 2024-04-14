package br.com.jhage.sew_with_me.domain.domain;

import br.com.jhage.sew_with_me.domain.model.Order;

public interface OrderMapper {
	
	public Order convert(String source);
}
