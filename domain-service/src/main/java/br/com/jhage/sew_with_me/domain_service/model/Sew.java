package br.com.jhage.sew_with_me.domain_service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;

/**
 * 
 * @author Alexsander Melo
 * @since 30/03/2024
 *
 * @param <E>
 */

@Entity
@Table
public class Sew implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TypeSew type;
	
	@Enumerated(EnumType.STRING)
	private SewStatus status;

	private String describe;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date deliveryForecast;

	private Double value;
	
	@OneToMany(mappedBy = "sew", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<MaterialUsed> materials;

	@ManyToOne
	private Order order;
	

	Sew() {
		this.type = TypeSew.REPARO;
		this.deliveryForecast = new Date();
		this.value = ValoresConstante.DOUBLE_ZERO;
		this.status = SewStatus.NAO_INICIADO;
		this.materials = new HashSet<MaterialUsed>();
	}

	public Sew(String type, Date deliveryForecast, Double value) {

		this.type = TypeSew.get(type);
		this.deliveryForecast = deliveryForecast == null ? new Date() : deliveryForecast;
		this.value = value == null ? ValoresConstante.DOUBLE_ZERO : value;
		this.status = SewStatus.NAO_INICIADO;
		this.materials = new HashSet<MaterialUsed>();
	}
	
	public Sew(TypeSew type, Date deliveryForecast, Double value) {

		this.type = type;
		this.deliveryForecast = deliveryForecast == null ? new Date() : deliveryForecast;
		this.value = value == null ? ValoresConstante.DOUBLE_ZERO : value;
		this.status = SewStatus.NAO_INICIADO;
		this.materials = new HashSet<MaterialUsed>();
	}
	
	public void addMaterials (MaterialUsed material) {

		if (this.materials != null) {
			this.materials = new HashSet<MaterialUsed>();
		}
		
		this.materials.add(material);
	}
	
	public Sew addOrder (Order order) {

		if (order != null) {
			this.order = order;
		}
		return this;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public TypeSew getType() {
		return type;
	}

	public String getDescribe() {
		return describe;
	}

	public Date getDeliveryForecast() {
		return deliveryForecast;
	}

	public Double getValue() {
		return value;
	}

	public SewStatus getStatus() {
		return status;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.type == null) ? ValoresConstante.ZERO : this.type.hashCode());
		result = prime * result
				+ ((this.deliveryForecast == null) ? ValoresConstante.ZERO : this.deliveryForecast.hashCode());
		result = prime * result + ((this.value == null) ? ValoresConstante.ZERO : this.value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sew)) {
			return false;
		}
		Sew other = (Sew) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.type.equals(other.type)
				&& this.deliveryForecast.equals(other.deliveryForecast) && this.value.equals(other.value);
	}

}
