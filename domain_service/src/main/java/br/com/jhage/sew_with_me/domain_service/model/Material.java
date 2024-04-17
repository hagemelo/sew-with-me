package br.com.jhage.sew_with_me.domain_service.model;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 * 
 * @author Alexsander Melo
 * @since 30/03/2024
 *
 * @param <E>
 */

@Entity
@Table
public class Material implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "mat_id", nullable = false)
	@SequenceGenerator(name = "matid", sequenceName = "GEN_MAT_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matid")
	private Long id;
	
	private String name;
	
	private String describe;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	Material() {
	}

	public Material(String name,  String describe, String unit) {

		this.unit = Unit.get(unit);
		this.describe = describe == null ? ValoresConstante.STRING_VAZIO : describe;
		this.name = name == null ? ValoresConstante.STRING_VAZIO : name;
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getDescribe() {
		return describe;
	}

	public Unit getUnit() {
		return unit;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.describe == null) ? ValoresConstante.ZERO : this.describe.hashCode());
		result = prime * result
				+ ((this.name == null) ? ValoresConstante.ZERO : this.name.hashCode());
		result = prime * result + ((this.unit == null) ? ValoresConstante.ZERO : this.unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Material)) {
			return false;
		}
		Material other = (Material) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.describe.equals(other.describe)
				&& this.name.equals(other.name) && this.unit.equals(other.unit);
	}

}
