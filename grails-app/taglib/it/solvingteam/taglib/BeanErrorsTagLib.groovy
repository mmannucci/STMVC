package it.solvingteam.taglib

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
	
	def stHasErrors ={ attrs, body ->
		
		
		def mybean = attrs.bean
		System.out.println("...xx" + mybean);
		if (mybean.domainErrors && !mybean.domainErrors.isEmpty()){
			out << body()
		}
		
	}	
	
	

	
	/*def hasPropertyError ={ attrs ->
		
		def mybean = attrs.bean
		//System.out.println("dentro taglib2:" + mybean.customErrors);
		def codec = attrs.codec ?: 'HTML'
		if (codec=='none') codec = ''
		def exist = mybean.domainErrors.find{it.code.equals(attrs.property)}
		return exist != null
	
    }	*/
	
	def hasPropertyError ={ attrs ->
		
		def mybean = attrs.bean
		def exist = mybean.domainErrors.find{it.field.equals(attrs.beanProperty)}
		if (exist) out << "errors"		
	}	

}
