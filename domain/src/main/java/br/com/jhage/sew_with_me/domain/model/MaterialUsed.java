package br.com.jhage.sew_with_me.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.jhage.sew_with_me.domain.helper.ValoresConstante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class MaterialUsed implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "matused_id", nullable = false)
	@SequenceGenerator(name = "matusedid", sequenceName = "GEN_MATUSED_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matusedid")
	private Long id;
	
	
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	@JsonBackReference
	@ManyToOne
	private Material material;
	
	@JsonBackReference
	@ManyToOne
	private Sew sew;
	
	
	MaterialUsed() {
	}

	public MaterialUsed(Integer quantity, String unit) {

		this.unit = Unit.get(unit);
		this.quantity = quantity == null ? ValoresConstante.ZERO : quantity;
	}
	
	public MaterialUsed addSew(Sew sew) {
		
		if(sew !=null) {
			this.sew = sew;
		}
		return this;
	}
	
	public MaterialUsed addMaterial(Material material) {
		
		if(material !=null) {
			this.material = material;
		}
		return this;
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Unit getUnit() {
		return unit;
	}

	public Material getMaterial() {
		return material;
	}

	public Sew getSew() {
		return sew;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.quantity == null) ? ValoresConstante.ZERO : this.quantity.hashCode());
		result = prime * result + ((this.unit == null) ? ValoresConstante.ZERO : this.unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MaterialUsed)) {
			return false;
		}
		MaterialUsed other = (MaterialUsed) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.quantity.equals(other.quantity)
				 && this.unit.equals(other.unit);
	}

}
