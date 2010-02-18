package it.prova.model;

import it.prova.util.HibernateUtil;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
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

@Component 
@Scope("prototype")
@Entity
public class Editore {
	
	private Long id;
	private Long version;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	@NotNull
	@Size(min = 1, max = 7, message = "{error.size}")
	private String nome;
	
	public Editore() {
		
	}
	
	public Editore(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name = "version")
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Editore:" + id;
	}
	
	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "editore");
		validator.validate(this, errors);
		domainErrors = errors.getAllErrors();
		return (domainErrors.isEmpty());
	}
	
	public static Editore create(){
		ApplicationContext ctx = ApplicationHolder.getApplication().getMainContext();
		return (Editore)ctx.getBean("editore");
	}
	
	public static Editore get(Long id) {
		return (Editore) HibernateUtil.sessionFactory().getCurrentSession().get(Editore.class, id);
	}
	
	public static List<Editore> list() {
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from Editore");
		return (List<Editore>)q.list();
	}
	
	public static List<Editore> findAll(int offset, int max, String sort, String order) {
		
		String sortFragment = "";
		if (sort != null && order != null) {
			sortFragment = " order by " + sort + " " + order;
		}
		
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from Editore" + sortFragment);
		q.setFirstResult(offset);
		q.setMaxResults(max);
		return (List<Editore>)q.list();
	}
	
	public static long count() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().createQuery("select count(*) from Editore").uniqueResult();
	}
		
	public Long save() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public Editore update() {
		return (Editore) HibernateUtil.sessionFactory().getCurrentSession().merge(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}
}
