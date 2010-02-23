package it.prova.model;


import it.prova.util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import java.util.*;

@Component 
@Scope("prototype")
@Entity
public class Libro {
	
	
	private Long id;
	private Long version;

	@Autowired
	private Validator validator;

	@Transient
	private List<ObjectError> domainErrors;
	
	
	private String titolo;
	private Integer pagine;
	private Autore autore;

	
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
