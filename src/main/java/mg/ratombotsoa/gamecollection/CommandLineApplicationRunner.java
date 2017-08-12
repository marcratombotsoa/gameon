package mg.ratombotsoa.gamecollection;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
	
	@Override
	public void run(String... args) throws Exception {
		consoleDao.saveAll(consoles);
		gameDao.saveAll(games);
		
		User user = new UserBuilder().withFirstName("Kevin")
			.withName("Durant")
			.withUsername("kd35")
			.build();
		
		userDao.save(user);
	}
}
