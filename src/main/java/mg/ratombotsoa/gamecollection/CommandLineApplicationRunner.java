package mg.ratombotsoa.gamecollection;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.User;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.model.builder.UserBuilder;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.repository.UserRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;

@Component
public class CommandLineApplicationRunner implements CommandLineRunner {

	@Autowired
	private List<Console> consoles;
	
	@Autowired
	private List<VideoGame> games;
	
	@Autowired
	private ConsoleRepository consoleDao;
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		consoleDao.saveAll(consoles);
		gameDao.saveAll(games);
		
		User user1 = new UserBuilder().withFirstName("Kevin")
			.withName("Durant")
			.withUsername("kd35")
			.withBirthDate(new Date(591541436000L))
			.withPassword(passwordEncoder.encode("password"))
			.build();
		
		userDao.save(user1);
		
		User user2 = new UserBuilder().withFirstName("Kobe")
			.withName("Bryant")
			.withUsername("blackmamba")
			.withBirthDate(new Date(272725436000L))
			.withPassword(passwordEncoder.encode("password"))
			.build();
			
			userDao.save(user2);
	}
}
