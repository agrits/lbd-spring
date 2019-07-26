package pl.fis.artur.kasza.lbdspring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShipAlreadyInFleetException extends Exception {

	public ShipAlreadyInFleetException(Spaceship ship) {
		super(String.format("Ship '%s' already exists in fleet", ship.getName()));
		
	}
	
}
