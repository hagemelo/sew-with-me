package br.com.jhage.sew_with_me.domain.helper;

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
public enum SewStatus implements Serializable {

	NAO_INICIADO("NÃO INICIADO"), EM_PREPARACAO("EM PREPARAÇÃO"), PARADO("PARADO"), FINALIZADO("FINALIZADO"),
	ENTREGUE("ENTREGUE");

	private static Map<String, SewStatus> namesMap = new HashMap<String, SewStatus>();

	private final String descricao;

	SewStatus(final String descricao) {

		this.descricao = descricao;
	}

	public static SewStatus get(String find) {

		SewStatus result = NAO_INICIADO;
		try {

			result = valueOf(SewStatus.class, find);
		} catch (IllegalArgumentException e) {

			System.out.println("TypeSew Nao Encontrado");
		}
		return result;
	}

	@JsonCreator
	public static SewStatus forValue(String value) {
		
		for (SewStatus enumConstant : SewStatus.class.getEnumConstants()) {
			
			namesMap.put(enumConstant.descricao, enumConstant);
		}
		
		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, SewStatus> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		return null;
	}

}
