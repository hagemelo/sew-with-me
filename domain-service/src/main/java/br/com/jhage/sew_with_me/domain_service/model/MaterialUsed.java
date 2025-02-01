package br.com.jhage.sew_with_me.domain_service.model;

import jakarta.persistence.*;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;


/**
 * 
 * @author Alexsander Melo
 * @since 30/03/2024
 *
 * @param <E>
 */

@Entity
@Table(name = "tb_material_used")
public class MaterialUsed implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	@ManyToOne
	@JoinColumn(name = "material_id", nullable = false)
	private Material material;
	
	@ManyToOne 
	@JoinColumn(name = "sew_id", nullable = false)
	private Sew sew;
	
	
	MaterialUsed() {
		
		this.unit = Unit.ML;
		this.quantity = ValoresConstante.ZERO;
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
