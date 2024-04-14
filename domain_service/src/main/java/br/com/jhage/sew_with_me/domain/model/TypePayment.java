package br.com.jhage.sew_with_me.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Alexnsander Melo
 * @since 30/03/2024
 *
 */
public enum TypePayment implements Serializable {

	PIX("PIX"), 
	DINHEIRO("DINHEIRO"),
	AGENDADO("AGENDADO");

	private static Map<String, TypePayment> namesMap = new HashMap<String, TypePayment>();

	private final String descricao;

	TypePayment(final String descricao) {

		this.descricao = descricao;
	}

	public static TypePayment get(String find) {

		TypePayment result = DINHEIRO;
		try {

			result = valueOf(TypePayment.class, find);
		} catch (IllegalArgumentException e) {

			System.out.println("TypeSew Nao Encontrado");
		}
		return result;
	}

	@JsonCreator
	public static TypePayment forValue(String value) {
		
		for (TypePayment enumConstant : TypePayment.class.getEnumConstants()) {
			
			namesMap.put(enumConstant.descricao, enumConstant);
		}
		
		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, TypePayment> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}

}
