package it.prova.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.prova.util.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Entity
public class Editore {
	
	private Long id;
	private Long version;
	
	@NotNull
	@Size(min = 1, max = 7, message = "{error.size}")
	private String nome;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	public Editore() {
		
	}
	
	public Editore(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(generator="hibseq")
	@GenericGenerator(name="hibseq", strategy = "seqhilo",
		    parameters = {
		        @Parameter(name="max_lo", value = "5"),
		        @Parameter(name="sequence", value="heybabyhey")
		    }
	)
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
	
	public static Editore get(Long id) {
		return (Editore) HibernateUtil.sessionFactory().getCurrentSession().get(Editore.class, id);
	}
	
	public static Set<Editore> list() {
		// qui bisogna fare una query...
		return new HashSet<Editore>();
	}
	
	public static Set<Editore> findAll(int offset, int max) {
		// qui bisogna fare una query...
		return new HashSet<Editore>();
	}
	
	public static int count() {
		// qui bisogna fare una query...
		return 0;
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
