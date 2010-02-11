package it.prova.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;

@Entity
public class Libro {
	
	private Long id;
	private String titolo;
	private Integer pagine;
	private Autore autore;
	private Long version;
	
	public Libro(){
		
	}
	
	@Id @GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column (name="titolo",nullable=false)
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	@Column (name="pagine",nullable=false)
	public Integer getPagine() {
		return pagine;
	}
	public void setPagine(Integer pagine) {
		this.pagine = pagine;
	}
	
	@ManyToOne
	@JoinColumn(name="fk_autore")
	public Autore getAutore() {
		return autore;
	}
	public void setAutore(Autore autore) {
		this.autore = autore;
	}
	@Version
	@Column(name="version")
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", id=" + id + ", pagine=" + pagine
				+ ", titolo=" + titolo + "]";
	}
	
	
	

}
