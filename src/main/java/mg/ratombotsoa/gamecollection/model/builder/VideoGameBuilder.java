package mg.ratombotsoa.gamecollection.model.builder;

import java.util.Date;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;

public class VideoGameBuilder {

	private VideoGame videoGame;
	
	public VideoGameBuilder() {
		videoGame = new VideoGame();
	}
	
	public VideoGame build() {
		return videoGame;
	}
	
	public VideoGameBuilder withId(Long id) {
		videoGame.setId(id);
		return this;
	}
	
	public VideoGameBuilder withName(String name) {
		videoGame.setName(name);
		return this;
	}
	
	public VideoGameBuilder withConsole(Console console) {
		videoGame.setConsole(console);
		return this;
	}
	
	public VideoGameBuilder withReleaseDate(Date date) {
		videoGame.setReleaseDate(date);
		return this;
	}
	
	public VideoGameBuilder withPublisher(String publisher) {
		videoGame.setPublisher(publisher);
		return this;
	}
}
