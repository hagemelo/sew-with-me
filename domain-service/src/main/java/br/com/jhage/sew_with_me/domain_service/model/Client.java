package br.com.jhage.sew_with_me.domain_service.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;


/**
 * 
 * @author Alexsander Melo
 * @since 30/03/2024
 *
 * @param <E>
 */

@Entity
@Table(name = "tb_client")
public class Client implements JhageEntidade{
	

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	private String contact;
	
	
	Client() {
	}
	
	public Client(String name, String contact) {

		this.name = name == null? "": name ;
		this.contact = contact == null? "": contact;
	}
	

	@Override
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	@JsonIgnore
	public String getJsonValue() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.name == null) ? ValoresConstante.ZERO : this.name.hashCode());
		result = prime * result + ((this.contact == null) ? ValoresConstante.ZERO : this.contact.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.name.equals(other.name)
				&& this.contact.equals(other.contact);
	}

}
