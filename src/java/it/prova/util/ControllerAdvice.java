package it.prova.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class ControllerAdvice {
	@Before("within(*..*Controller)")
	public void setRequestParameters(JoinPoint pjp) {
		Signature sig = pjp.getSignature();
		
		String declaringTypeName = sig.getDeclaringType().getSimpleName(); 
		String controllerName = declaringTypeName.substring(0, declaringTypeName.indexOf("Controller")).toLowerCase();
		
		String actionName = sig.getName();
		RequestContextHolder.currentRequestAttributes().setAttribute(GrailsApplicationAttributes.CONTROLLER_NAME_ATTRIBUTE,controllerName, RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.currentRequestAttributes().setAttribute(GrailsApplicationAttributes.ACTION_NAME_ATTRIBUTE, actionName, RequestAttributes.SCOPE_REQUEST);
	}
}
