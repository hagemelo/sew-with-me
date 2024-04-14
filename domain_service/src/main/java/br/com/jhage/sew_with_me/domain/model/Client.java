package br.com.jhage.sew_with_me.domain.model;

import br.com.jhage.sew_with_me.domain.helper.ValoresConstante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Client implements JhageEntidade{
	

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "client_id", nullable = false)
	@SequenceGenerator(name = "clientid", sequenceName = "GEN_CLIENT_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientid")
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
