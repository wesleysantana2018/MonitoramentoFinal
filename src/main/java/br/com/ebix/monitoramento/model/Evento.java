package br.com.ebix.monitoramento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento {

	@Id 
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String url;
	
	private String nmEvento;
	
	//@Transient
	private String nmStatus;
	
	private String nmEmail;

	private String tipo;
	
	@Temporal(TemporalType.TIMESTAMP) // yyyy-MM-dd HH:mm:ss
	private Date dataHora;
		
	
	//METODO CONSTRUTOR
	public Evento() {
		super();
		
	}

	public Evento(String url, String nmEvento, String nmStatus, String nmEmail) {
		super();
		this.url = url;
		this.nmEvento = nmEvento;
		this.nmStatus = nmStatus;
		this.nmEmail = nmEmail;
	}

	//GETS E SETS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNmEvento() {
		return nmEvento;
	}

	public void setNmEvento(String nmEvento) {
		this.nmEvento = nmEvento;
	}

	public String getNmStatus() {
		return nmStatus;
	}

	public void setNmStatus(String nmStatus) {
		this.nmStatus = nmStatus;
	}

	public String getNmEmail() {
		return nmEmail;
	}

	public void setNmEmail(String nmEmail) {
		this.nmEmail = nmEmail;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", url=" + url + ", nmEvento=" + nmEvento + ", nmStatus=" + nmStatus + ", nmEmail="
				+ nmEmail + "]";
	}
	
}
