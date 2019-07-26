package pl.fis.artur.kasza.lbdspring.model;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

public class ErrorMessagePair {
	private String fieldName;
	private String message;
	

	
	public ErrorMessagePair(FieldError fe, Locale locale, MessageSource messageSource) {
		this.fieldName = fe.getObjectName();
		this.message = messageSource.getMessage(fe.getCode(), fe.getArguments(), locale);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
