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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Component 
@Scope("prototype")
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
	
	private Editore editore;

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
	
	@ManyToOne
	@JoinColumn(name = "fk_editore")
	public Editore getEditore() {
		return editore;
	}
	
	public void setEditore(Editore editore) {
		this.editore = editore;
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

		return "Nome:" + nome;
	}

	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "autore");
		validator.validate(this, errors);
		domainErrors = errors.getAllErrors();
		return (domainErrors.isEmpty());

	}

	public static Autore create(){
		ApplicationContext ctx = ApplicationHolder.getApplication().getMainContext();
		return (Autore)ctx.getBean("autore");
	}
	
	public static Autore get(Long id) {
		Session session = HibernateUtil.sessionFactory().getCurrentSession(); 
		return (Autore) session.get(Autore.class, id);
	}
	
	public static List<Autore> list() {
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from Autore");
		return (List<Autore>)q.list();
	}
	
	public static List<Autore> findAll(int offset, int max, String sort, String order) {
		
		String sortFragment = "";
		if (sort != null && order != null) {
			sortFragment = " order by " + sort + " " + order;
		}
		
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from Autore" + sortFragment);
		q.setFirstResult(offset);
		q.setMaxResults(max);
		return (List<Autore>)q.list();
	}
	
	public static long count() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().createQuery("select count(*) from Autore").uniqueResult();
	}
		
	public Long save() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public Autore update() {
		return (Autore) HibernateUtil.sessionFactory().getCurrentSession().merge(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}

}
