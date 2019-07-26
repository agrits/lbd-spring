package pl.fis.artur.kasza.lbdspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String name) {
		super(String.format("Spaceship '%s' not found.", name));
	}
}
