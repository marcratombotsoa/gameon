package mg.ratombotsoa.gamecollection;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;

@Component
public class CommandLineApplicationRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineApplicationRunner.class);
	
	@Autowired
	private List<Console> consoles;
	
	@Autowired
	private List<VideoGame> games;
	
	@Autowired
	private ConsoleRepository consoleDao;
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Override
	public void run(String... args) throws Exception {
		consoleDao.saveAll(consoles);
		gameDao.saveAll(games);
		
		List<Console> releaseDatesortedConsoles = consoleDao.findAll(Sort.by(Direction.DESC, "releaseDate"));
		LOGGER.debug("List of consoles sorted by descending release date: {}", releaseDatesortedConsoles);
		
		List<VideoGame> sortedGames = gameDao.findAll(Sort.by(Direction.ASC, "console.name", "releaseDate"));
		LOGGER.debug("List of video games sorted by console and then by release date: {}", sortedGames);
		
		LOGGER.debug("Search game by name: {}", gameDao.findByName("NBA 2K17"));
		LOGGER.debug("Search console by name: {}", consoleDao.findByName("Playstation 4"));
	}
}
