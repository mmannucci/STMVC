 
class BeanErrorsTagLib {
	
	def beanErrors ={ attrs ->
		
		def mybean = attrs.bean
			//System.out.println("dentro taglib2:" + mybean.customErrors);
			def codec = attrs.codec ?: 'HTML'
			if (codec=='none') codec = ''
			mybean.domainErrors.each {out << "${message(error:it, encodeAs:codec)} <br>" }
	}
	
	def beanPropertyError ={ attrs ->
		
		def mybean = attrs.bean
		//System.out.println("dentro taglib2:" + mybean.customErrors);
		def codec = attrs.codec ?: 'HTML'
		if (codec=='none') codec = ''
		mybean.domainErrors.each {if (it.field.equals(attrs.property)) {out << "${message(error:it, encodeAs:codec)} <br>" } }
	}
	
	

}
