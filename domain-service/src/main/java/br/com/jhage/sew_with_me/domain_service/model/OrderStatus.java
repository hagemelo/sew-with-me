package br.com.jhage.sew_with_me.domain_service.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexnsander Melo
 * @since 30/03/2024
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus implements Serializable {

	ABERTO("ABERTO"), 
	ATRASADO("ATRASADO"),
	CANCELADO("CANCELADO");

	private static Map<String, OrderStatus> namesMap = new HashMap<String, OrderStatus>();

	private final String descricao;

	OrderStatus(final String descricao) {

		this.descricao = descricao;
	}

	public static OrderStatus get(String find) {

		OrderStatus result = ABERTO;
		try {

			result = valueOf(OrderStatus.class, find);
		} catch (IllegalArgumentException e) {

			System.out.println("TypeSew Nao Encontrado");
		}
		return result;
	}

	@JsonCreator
	public static OrderStatus forValue(String value) {
		
		for (OrderStatus enumConstant : OrderStatus.class.getEnumConstants()) {
			
			namesMap.put(enumConstant.descricao, enumConstant);
		}
		
		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, OrderStatus> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}

}
