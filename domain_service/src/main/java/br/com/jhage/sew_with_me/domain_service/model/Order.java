package br.com.jhage.sew_with_me.domain_service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Order implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "order_id", nullable = false)
	@SequenceGenerator(name = "orderid", sequenceName = "GEN_ORDER_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderid")
	private Long id;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date created_at;

	private Double value;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@Temporal(TemporalType.DATE)
	private Date deliveryForecast;

	@ManyToOne
	private Client client;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Sew> sews;

	Order() {
		this.created_at = new Date();
		this.value = ValoresConstante.DOUBLE_ZERO;
		this.deliveryForecast = new Date();
		this.status = OrderStatus.ABERTO;
		this.sews = new HashSet<Sew>();
	}

	public Order(Date deliveryForecast) {

		this.created_at = new Date();
		this.value =  ValoresConstante.DOUBLE_ZERO;
		this.deliveryForecast = deliveryForecast == null ? new Date() : deliveryForecast;
		this.status = OrderStatus.ABERTO;
		this.sews = new HashSet<Sew>();
	}

	public void setNotNull() {

		this.created_at = this.created_at == null ? new Date() : this.created_at;
		this.value = this.value == null ? ValoresConstante.DOUBLE_ZERO : this.value;
		this.deliveryForecast = this.deliveryForecast ==null? new Date(): this.deliveryForecast;
		this.sews = this.sews == null ?  new HashSet<Sew>() : this.sews;
	}

	public Order addClient(Client client) {

		if (client != null) {
			this.client = client;
		}
		return this;
	}

	public Order addSew(Sew sew) {

		if (sew != null) {
			this.sews = new HashSet<Sew>();
		}
		this.value += sew.getValue();
		this.sews.add(sew);
		return this;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public Double getValue() {
		return value;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Date getDeliveryForecast() {
		return deliveryForecast;
	}

	public Client getClient() {
		return client;
	}

	public Set<Sew> getSews() {
		return this.sews;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.status == null) ? ValoresConstante.ZERO : this.status.hashCode());
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
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.status.equals(other.status)
				&& this.deliveryForecast.equals(other.deliveryForecast) && this.value.equals(other.value);
	}

}
