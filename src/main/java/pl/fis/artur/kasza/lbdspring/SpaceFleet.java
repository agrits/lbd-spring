package pl.fis.artur.kasza.lbdspring;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "fleet")
public class SpaceFleet {
	private String name;
	private List<Spaceship> ships;
	public SpaceFleet(String name, List<Spaceship> ships) {
		super();
		this.name = name;
		this.ships = ships;
	}
	
	public SpaceFleet() {
		
	}
	
	public SpaceFleet(String name) {
		this.name = name;
		this.ships = new ArrayList<Spaceship>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JacksonXmlElementWrapper(localName = "ships")
	@JacksonXmlProperty(localName = "ship")
	public List<Spaceship> getShips() {
		return ships;
	}
	public void setShips(List<Spaceship> ships) {
		this.ships = ships;
	}
	
	public Spaceship addShip(Spaceship ship) throws ShipAlreadyInFleetException {
		if(this.ships.stream().noneMatch(s -> s.getName().contentEquals(ship.getName()))) {
			this.ships.add(ship);
			return ship;
		}
		else
			throw new ShipAlreadyInFleetException(ship);
	}

	public Spaceship findShipByName(String name) throws ResourceNotFoundException {
		for(Spaceship ship : ships) {
			if(ship.getName().contentEquals(name))
				return ship;
		}
		throw new ResourceNotFoundException(name);
	}

	public void removeSpaceship(String name) {
		for(Spaceship ship : ships) {
			if(ship.getName().contentEquals(name)) {
				ships.remove(ship);
				break;
			}
		}
	}
	
}
