package mg.ratombotsoa.gamecollection;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.service.ConsoleService;
import mg.ratombotsoa.gamecollection.service.GameService;
import mg.ratombotsoa.gamecollection.util.comparator.CustomGameComparator;
import mg.ratombotsoa.gamecollection.util.comparator.ReleaseDateComparator;

@Component
public class CommandLineApplicationRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineApplicationRunner.class);
	
	@Autowired
	private List<Console> consoles;
	
	@Autowired
	private ConsoleService consoleService;
	
	@Autowired
	private List<VideoGame> games;
	
	@Autowired
	private GameService gameService;
	
	@Override
	public void run(String... args) throws Exception {
		sortConsoles();
		sortGames();
	}
	
	private void sortConsoles() {
		LOGGER.debug("List of consoles: {}", consoles);
		consoleService.sortConsoles(consoles, new ReleaseDateComparator(), true);
		LOGGER.debug("List of consoles after sorting by release date: {}", consoles);
	}
	
	private void sortGames() {
		LOGGER.debug("List of games: {}", games);
		gameService.sortGames(games, new CustomGameComparator(), true);
		LOGGER.debug("List of games after sorting by console then by release date: {}", games);
	}
	
}
