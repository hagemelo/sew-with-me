package br.com.jhage.sew_with_me.domain.helper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.sew_with_me.domain.domain.OrderMapper;
import br.com.jhage.sew_with_me.domain.model.Order;

/**
 * 
 * @author Alexsander Melo
 * @since 02/04/2024
 *
 */
@Component
public class OrderMapperImpl implements OrderMapper {

	@Override
	public Order convert(String source) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			Order order = objectMapper.readValue(source, Order.class);

			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
