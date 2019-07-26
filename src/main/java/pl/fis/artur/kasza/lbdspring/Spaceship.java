package pl.fis.artur.kasza.lbdspring;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "spaceship")
public class Spaceship {
	
	@NotBlank
	@NotNull
	private String name;
	
	@Positive
	@NotNull
	private double speed;
	
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate yearOfManufacturing;
	
	
	public Spaceship(String name, double speed, LocalDate yearOfManufacturing) {
		this.name = name;
		this.speed = speed;
		this.yearOfManufacturing = yearOfManufacturing;
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
	
	
	
}
