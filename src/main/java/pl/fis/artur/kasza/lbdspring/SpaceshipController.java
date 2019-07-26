package pl.fis.artur.kasza.lbdspring;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpaceshipController {

	@Autowired
	private DataStorage dataStorage;
	
	@RequestMapping(
			value = "/spaceships", 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public SpaceFleet getSpaceFleet(){
		return dataStorage.getSpaceFleet();
	}
	
	@RequestMapping(
		value = "/spaceships/{name}", 
		method = RequestMethod.GET,
		produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public Spaceship getSpaceship(@PathVariable(name = "name") String name) 
			throws ResourceNotFoundException{
		return dataStorage.getSpaceFleet().findShipByName(name);
	}
	
	@RequestMapping(
		value = "/spaceships", 
		method = RequestMethod.POST,
		produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
	public Spaceship addSpaceship(@Valid @RequestBody Spaceship ship) 
			throws ResourceNotFoundException, ShipAlreadyInFleetException{
		return dataStorage.getSpaceFleet().addShip(ship);
	}
	
	@RequestMapping(
			value = "/spaceships/{name}", 
			method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

		public ResponseEntity deleteSpaceship(@PathVariable(name = "name") String name){
			dataStorage.getSpaceFleet().removeSpaceship(name);
			return ResponseEntity.ok().build();
		}
	
	
}
