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
public enum TypeSew implements Serializable {

	REPARO, AJUSTE, NOVO;

	private static Map<String, TypeSew> namesMap = new HashMap<String, TypeSew>();

	public static TypeSew get(String find) {

		TypeSew result = NOVO;
		try {
			result = valueOf(TypeSew.class, find);
		} catch (IllegalArgumentException e) {

			System.out.println("TypeSew Nao Encontrado");
		}
		return result;
	}

	@JsonCreator
	public static TypeSew forValue(String value) {

		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, TypeSew> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}

}
