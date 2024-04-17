package br.com.jhage.sew_with_me.domain_service.model;

import br.com.jhage.sew_with_me.domain_service.helper.ValoresConstante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * @since 02/04/2024
 *
 * @param <E>
 */

@Entity
@Table
public class Metrics implements JhageEntidade {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "metrics_id", nullable = false)
	@SequenceGenerator(name = "metricsid", sequenceName = "GEN_METRICS_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metricsid")
	private Long id;

	private String cintura;

	private String busto;

	private String abaixoBusto;

	private String quadril;

	private String ombro;

	private String manga;

	private String roupa;

	private String peito;

	private String punho;

	private String fundo;
	
	@ManyToOne
	private Sew sew;

	Metrics() {
	}

	public Metrics(String cintura, String busto, String abaixoBusto, String quadril, String ombro, String manga,
			String roupa, String peito, String punho, String fundo) {
		this.cintura = cintura;
		this.busto = busto;
		this.abaixoBusto = abaixoBusto;
		this.quadril = quadril;
		this.ombro = ombro;
		this.manga = manga;
		this.roupa = roupa;
		this.peito = peito;
		this.punho = punho;
		this.fundo = fundo;

	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Metrics addSew(Sew sew) {
		
		if(sew !=null) {
			this.sew = sew;
		}
		return this;
	}
	
	public String getCintura() {
		return cintura;
	}

	public String getBusto() {
		return busto;
	}

	public String getAbaixoBusto() {
		return abaixoBusto;
	}

	public String getQuadril() {
		return quadril;
	}

	public String getOmbro() {
		return ombro;
	}

	public String getManga() {
		return manga;
	}

	public String getRoupa() {
		return roupa;
	}

	public String getPeito() {
		return peito;
	}

	public String getPunho() {
		return punho;
	}

	public String getFundo() {
		return fundo;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ValoresConstante.ZERO : id.hashCode());
		result = prime * result + ((this.cintura == null) ? ValoresConstante.ZERO : this.cintura.hashCode());
		result = prime * result + ((this.busto == null) ? ValoresConstante.ZERO : this.busto.hashCode());
		result = prime * result + ((this.abaixoBusto == null) ? ValoresConstante.ZERO : this.abaixoBusto.hashCode());
		result = prime * result + ((this.quadril == null) ? ValoresConstante.ZERO : this.quadril.hashCode());
		result = prime * result + ((this.ombro == null) ? ValoresConstante.ZERO : this.ombro.hashCode());
		result = prime * result + ((this.manga == null) ? ValoresConstante.ZERO : this.manga.hashCode());
		result = prime * result + ((this.roupa == null) ? ValoresConstante.ZERO : this.roupa.hashCode());
		result = prime * result + ((this.peito == null) ? ValoresConstante.ZERO : this.peito.hashCode());
		result = prime * result + ((this.punho == null) ? ValoresConstante.ZERO : this.punho.hashCode());
		result = prime * result + ((this.fundo == null) ? ValoresConstante.ZERO : this.fundo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Metrics)) {
			return false;
		}
		Metrics other = (Metrics) obj;

		return super.equals(obj) && this.id.equals(other.id) && this.cintura.equals(other.cintura)
				&& this.busto.equals(other.busto) && this.quadril.equals(other.quadril)
				&& this.ombro.equals(other.ombro) && this.manga.equals(other.manga) && this.roupa.equals(other.roupa)
				&& this.peito.equals(other.peito) && this.punho.equals(other.punho) && this.fundo.equals(other.fundo)
				&& this.abaixoBusto.equals(other.abaixoBusto);
	}

}
