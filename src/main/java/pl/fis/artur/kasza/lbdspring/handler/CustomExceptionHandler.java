package pl.fis.artur.kasza.lbdspring.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.fis.artur.kasza.lbdspring.model.ValidationErrorMessage;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorMessage> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(new ValidationErrorMessage(e, request.getLocale(), messageSource));
	}
}
