package br.com.jhage.sew_with_me.domain.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Payment implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "pag_id", nullable = false)
	@SequenceGenerator(name = "pagid", sequenceName = "GEN_PAG_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagid")
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date created_at;
	
	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date payment_date;
	
	private Double value;
	
	@JsonBackReference
	@ManyToOne
	private Order order;
	
	@Enumerated(EnumType.STRING)
	private TypePayment type;
	

	Payment() {
	}

	public Payment(String type, Date payment_date, Double value) {

		this.type = TypePayment.get(type);
		this.created_at = new Date();
		this.value = value == null ? ValoresConstante.DOUBLE_ZERO : value;
		this.payment_date = payment_date == null ? new Date() : payment_date;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	public Payment addOrder(Order order) {
		
		if(order !=null) {
			this.order = order;
		}
		return this;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public Double getValue() {
		return value;
	}

	public Order getOrder() {
		return order;
	}

	public TypePayment getType() {
		return type;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.type == null) ? ValoresConstante.ZERO : this.type.hashCode());
		result = prime * result
				+ ((this.payment_date == null) ? ValoresConstante.ZERO : this.payment_date.hashCode());
		result = prime * result + ((this.value == null) ? ValoresConstante.ZERO : this.value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Payment)) {
			return false;
		}
		Payment other = (Payment) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.type.equals(other.type)
				&& this.payment_date.equals(other.payment_date) && this.value.equals(other.value);
	}
	

}
