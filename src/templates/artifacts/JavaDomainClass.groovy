@artifact.package@import it.prova.util.HibernateUtil;

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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
public class @artifact.name@ {
	
	private Long id;
	private Long version;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	public @artifact.name@() {
		
	}
	
	public @artifact.name@(Long id) {
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
	
	
	public String toString() {
		return "@artifact.name@:" + id;
	}
	
	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "@artifact.propertyName@");
		validator.validate(this, errors);
		domainErrors = errors.getAllErrors();
		return (domainErrors.isEmpty());
	}
	
	public static @artifact.name@ create(){
		ApplicationContext ctx = ApplicationHolder.getApplication().getMainContext();
		return (@artifact.name@)ctx.getBean("@artifact.propertyName@");
	}
	
	public static @artifact.name@ get(Long id) {
		return (@artifact.name@) HibernateUtil.sessionFactory().getCurrentSession().get(@artifact.name@.class, id);
	}
	
	public static List<@artifact.name@> list() {
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from @artifact.name@");
		return (List<@artifact.name@>)q.list();
	}
	
	public static List<@artifact.name@> findAll(int offset, int max) {
		Query q = HibernateUtil.sessionFactory().getCurrentSession().createQuery("from @artifact.name@");
		q.setFirstResult(offset);
		q.setMaxResults(max);
		return (List<@artifact.name@>)q.list();
	}
	
	public static int count() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().createQuery("select count(*) from @artifact.name@").uniqueResult();
	}
		
	public Long save() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public @artifact.name@ update() {
		return (@artifact.name@) HibernateUtil.sessionFactory().getCurrentSession().merge(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}
}
