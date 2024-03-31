package br.com.jhage.sew_with_me.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Unit  implements Serializable {
	
	KG,
	GR,
	LT,
	ML,
	CX,
	LA
	;
	
	private static Map<String, Unit> namesMap = new HashMap<String, Unit>();

	
	public static Unit get(String find) {

		Unit result = KG;
		try{
			result  = valueOf(Unit.class, find);
		}catch (IllegalArgumentException e) {
			
			System.out.println("Tamanho Nao Encontrado");
		}
		return result;
	}
	
	
	@JsonCreator
    public static Unit forValue(String value) {
		
        return namesMap.get(value);
    }
	
	public static Set<Unit> getAll(){
		
		Set<Unit> result = new HashSet<>();
		namesMap.entrySet().forEach(e -> result.add(e.getValue()));
		return result;
	}
	
	@JsonValue
    public String toValue() {
        for (Entry<String, Unit> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null;
    }

}
