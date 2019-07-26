package pl.fis.artur.kasza.lbdspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.fis.artur.kasza.lbdspring.model.Spaceship;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShipAlreadyInFleetException extends Exception {

	public ShipAlreadyInFleetException(Spaceship ship) {
		super(String.format("Ship '%s' already exists in fleet", ship.getName()));
		
	}
	
}
