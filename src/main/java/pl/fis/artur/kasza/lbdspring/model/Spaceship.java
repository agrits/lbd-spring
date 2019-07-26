package pl.fis.artur.kasza.lbdspring.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import pl.fis.artur.kasza.lbdspring.controller.SpaceshipController;
import pl.fis.artur.kasza.lbdspring.util.Constants;

@JsonInclude(value = Include.NON_NULL)
@JacksonXmlRootElement(localName = "spaceship")
public class Spaceship extends ResourceSupport{
	
	@NotBlank
	@NotNull
	private String name;
	
	@Positive
	@NotNull
	private double speed;
	
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate yearOfManufacturing;
	
	
	
	
	public Spaceship(String name, double speed, LocalDate yearOfManufacturing){
		this.name = name;
		this.speed = speed;
		this.yearOfManufacturing = yearOfManufacturing;
		this.add(ControllerLinkBuilder.linkTo(SpaceshipController.class)
										.slash(name)
										.withSelfRel());
	}
	
	public Spaceship() {
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public LocalDate getYearOfManufacturing() {
		return yearOfManufacturing;
	}
	public void setYearOfManufacturing(LocalDate yearOfManufacturing) {
		this.yearOfManufacturing = yearOfManufacturing;
	}
	
	public int compareTo(Spaceship s, String key){
		if(key.contentEquals(Constants.SPEED)) {
			return Double.compare(this.speed, s.getSpeed());
		}
		else {
			return this.name.compareTo(s.getName());
		}
	}
	
}
