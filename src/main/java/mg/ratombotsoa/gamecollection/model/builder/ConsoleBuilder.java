package mg.ratombotsoa.gamecollection.model.builder;

import java.util.Date;

import mg.ratombotsoa.gamecollection.model.Console;

public class ConsoleBuilder {

	private Console console;
	
	public ConsoleBuilder() {
		console = new Console();
	}
	
	public Console build() {
		return console;
	}
	
	public ConsoleBuilder withId(Long id) {
		console.setId(id);
		return this;
	}
	
	public ConsoleBuilder withName(String name) {
		console.setName(name);
		return this;
	}
	
	public ConsoleBuilder withManufacturer(String manufacturer) {
		console.setManufacturer(manufacturer);
		return this;
	}
	
	public ConsoleBuilder withReleaseDate(Date date) {
		console.setReleaseDate(date);
		return this;
	}
	
	public ConsoleBuilder withNumberOfBits(Integer bits) {
		console.setNumberOfBits(bits);
		return this;
	}
}
