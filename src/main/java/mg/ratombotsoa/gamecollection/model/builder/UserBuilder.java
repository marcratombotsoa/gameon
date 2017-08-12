package mg.ratombotsoa.gamecollection.model.builder;

import java.util.Date;
import java.util.List;

import mg.ratombotsoa.gamecollection.model.User;
import mg.ratombotsoa.gamecollection.model.VideoGame;

public class UserBuilder {

	private User user;
	
	public UserBuilder() {
		this.user = new User();
	}
	
	public User build() {
		return user;
	}
	
	public UserBuilder withName(String name) {
		user.setName(name);
		return this;
	}
	
	public UserBuilder withFirstName(String firstName) {
		user.setFirstName(firstName);
		return this;
	}
	
	public UserBuilder withBirthDate(Date birthDate) {
		user.setBirthDate(birthDate);
		return this;
	}
	
	public UserBuilder withUsername(String username) {
		user.setUsername(username);
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder addVideoGame(VideoGame game) {
		user.addGame(game);
		return this;
	}
	
	public UserBuilder addVideoGames(List<VideoGame> games) {
		games.forEach(game -> user.addGame(game));
		return this;
	}
}
