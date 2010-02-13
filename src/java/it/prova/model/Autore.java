package it.prova.model;

import it.prova.util.HibernateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
//import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.codehaus.groovy.grails.commons.spring.GrailsApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Entity
public class Autore {

	private Long id;
	private Long version;

	@Autowired
	private Validator validator;

	@Transient
	private List<ObjectError> domainErrors;

	@NotNull
	@Size(min = 1, max = 7, message = "{error.size}")
	private String nome;

	private String cognome;

	private Date date;
	private Set<Libro> libros = new HashSet<Libro>(0);

	public Autore() {

	}

	public Autore(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_autore")
	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cognome")
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Version
	@Column(name = "version")
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {

		return "Nome:" + nome + "....size libros:" + libros.size();
	}

	public boolean validate() {
		
		System.out
				.println("...............validate di Autore xx...................");
		BindingResult errors = new BeanPropertyBindingResult(this, "autore");
		validator.validate(this, errors);
		
		//errors.rejectValue("nome", "error.nome", "msgdefault");
		//errors.rejectValue("nome", "error.nome");
		//System.out.println(errors.getMessage());
		domainErrors = errors.getAllErrors();
		System.out.println("errors:" + domainErrors);
		for (ObjectError e : domainErrors) {
			System.out.println("getDefaultMessage:" + e.getDefaultMessage());
			System.out.println("getCode:" + e.getCode());

			// System.out.println("errore:" + e.);
		}

		return (domainErrors.isEmpty());

	}

	public static Autore get(Long id) {
		// System.out.println("...HibernateUtil.sessionFactory():" +
		// HibernateUtil.sessionFactory());
		return (Autore) HibernateUtil.sessionFactory().getCurrentSession().get(
				Autore.class, id);
	}

}
