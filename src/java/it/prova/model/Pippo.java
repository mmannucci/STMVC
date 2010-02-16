package it.prova.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.prova.util.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Entity
public class Pippo {
	
	private Long id;
	private Long version;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	public Pippo() {
		
	}
	
	public Pippo(Long id) {
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
	
	
	public String toString() {
		return "Pippo:" + id;
	}
	
	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "Pippo");
		validator.validate(this, errors);
		domainErrors = errors.getAllErrors();
		return (domainErrors.isEmpty());
	}
	
	public static Pippo get(Long id) {
		return (Pippo) HibernateUtil.sessionFactory().getCurrentSession().get(Pippo.class, id);
	}
	
	public static Set<Pippo> list() {
		// qui bisogna fare una query...
		return new HashSet<Pippo>();
	}
	
	public static Set<Pippo> findAll(int offset, int max) {
		// qui bisogna fare una query...
		return new HashSet<Pippo>();
	}
	
	public static int count() {
		// qui bisogna fare una query...
		return 0;
	}
		
	public Pippo save() {
		return (Pippo) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public Pippo update() {
		return (Pippo) HibernateUtil.sessionFactory().getCurrentSession().merge(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}
}
