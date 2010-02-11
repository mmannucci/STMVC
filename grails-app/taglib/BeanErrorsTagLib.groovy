 
class BeanErrorsTagLib {
	
	def beanErrors ={ attrs ->
		
		def mybean = attrs.bean
		//System.out.println("dentro taglib2:" + mybean.customErrors);
			mybean.domainErrors.each {out << "${it.defaultMessage} <br>" }
	}
	
	def beanPropertyError ={ attrs ->
		
		def mybean = attrs.bean
		//System.out.println("dentro taglib2:" + mybean.customErrors);
		mybean.domainErrors.each {if (it.field.equals(attrs.property)) {out << "${it.defaultMessage} <br>" } }
	}
	
	

}
