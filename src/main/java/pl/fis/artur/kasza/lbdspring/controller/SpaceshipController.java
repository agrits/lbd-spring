package pl.fis.artur.kasza.lbdspring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.fis.artur.kasza.lbdspring.component.DataStorage;
import pl.fis.artur.kasza.lbdspring.exception.ResourceNotFoundException;
import pl.fis.artur.kasza.lbdspring.exception.ShipAlreadyInFleetException;
import pl.fis.artur.kasza.lbdspring.model.SpaceFleet;
import pl.fis.artur.kasza.lbdspring.model.Spaceship;

@RestController
@RequestMapping("/space-fleet")
@CacheConfig
public class SpaceshipController {

	@Autowired
	private DataStorage dataStorage;
	
	@Cacheable("cache")
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public SpaceFleet getSpaceFleet(){
		return dataStorage.getSpaceFleet();
	}
	
	@Cacheable("cache")
	@GetMapping(
			value="/ships",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<Spaceship> getSpaceships(@RequestParam("key") String key,
									@RequestParam("mode") String mode){
		return dataStorage.getSpaceFleet().getSortedShips(key, mode);
	}
	
	@Cacheable("cache")
	@GetMapping(
		value = "/{name}", 
		produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public Spaceship getSpaceship(@PathVariable(name = "name") String name) 
			throws ResourceNotFoundException, InterruptedException{
		return dataStorage.getSpaceFleet().findShipByName(name);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addSpaceship(@Valid @RequestBody Spaceship ship) 
			throws ResourceNotFoundException, ShipAlreadyInFleetException{
		
		return ResponseEntity.status(HttpStatus.CREATED)
									.body(dataStorage.getSpaceFleet()
									.addShip(ship));
	}
	
	@DeleteMapping(
			value = "/{name}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

		public ResponseEntity<?> deleteSpaceship(@PathVariable(name = "name") String name){
			dataStorage.getSpaceFleet().removeSpaceship(name);
			return ResponseEntity.ok().build();
		}
}
