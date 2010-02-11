package it.prova.util;

import javax.persistence.Transient;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.codehaus.groovy.grails.commons.ApplicationHolder ;


public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		
		ApplicationContext ctx = ApplicationHolder.getApplication().getMainContext();
		sessionFactory = (SessionFactory)ctx.getBean("sessionFactory");
	}
	
	
	public static SessionFactory sessionFactory(){
		
		return sessionFactory;
		
	}
	
	public static Session getCurrentSession(){
		
		return sessionFactory!=null?sessionFactory.getCurrentSession():null;
		
	}
	


}
