package pl.fis.artur.kasza.lbdspring.model;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationErrorMessage {
	private String status;
	private List<ErrorMessagePair> fieldErrors;
	
	
	public ValidationErrorMessage(MethodArgumentNotValidException e, Locale locale, MessageSource messageSource) {
		this.status = HttpStatus.BAD_REQUEST.toString();
		this.fieldErrors = e.getBindingResult()
							.getFieldErrors()
							.stream()
							.map(fe -> new ErrorMessagePair(fe, locale, messageSource))
							.collect(Collectors.toList());
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ErrorMessagePair> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<ErrorMessagePair> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
