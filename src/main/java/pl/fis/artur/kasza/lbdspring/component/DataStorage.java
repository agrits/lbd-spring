package pl.fis.artur.kasza.lbdspring.component;

import java.time.LocalDate;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import pl.fis.artur.kasza.lbdspring.exception.ShipAlreadyInFleetException;
import pl.fis.artur.kasza.lbdspring.model.SpaceFleet;
import pl.fis.artur.kasza.lbdspring.model.Spaceship;

@Component
public class DataStorage implements ApplicationListener<ApplicationReadyEvent>{
	private final String[] SPACESHIP_NAMES = {"Żubr", "Bóbr", "Ryjówka", "Łoś", "Lis", "Zając"};
	private SpaceFleet spaceFleet;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		spaceFleet = new SpaceFleet("FIS Fleet");
		for(String name : SPACESHIP_NAMES) {
			Spaceship ship = new Spaceship(name, 3.5, LocalDate.parse("1998-07-22"));
			try {
				spaceFleet.addShip(ship);
			} catch (ShipAlreadyInFleetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public SpaceFleet getSpaceFleet() {
		return spaceFleet;
	}
	
	
	
}
