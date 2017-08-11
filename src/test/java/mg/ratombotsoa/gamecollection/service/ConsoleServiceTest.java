package mg.ratombotsoa.gamecollection.service;

import static org.junit.Assert.assertFalse;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mg.ratombotsoa.gamecollection.exception.ImpossibleToDeleteException;
import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.model.builder.ConsoleBuilder;
import mg.ratombotsoa.gamecollection.model.builder.VideoGameBuilder;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleServiceTest {

	@Autowired
	private ConsoleService consoleService;
	
	@Autowired
	private ConsoleRepository consoleDao;
	
	@Autowired
	private VideoGameRepository gameDao;
	
	private Console console1;
	private Console console2;
	
	@Before
	public void setup() {
		console1 = new ConsoleBuilder().withManufacturer("Sony")
				.withName("Game boy")
				.withNumberOfBits(32)
				.withReleaseDate(new Date())
				.build();
		consoleDao.save(console1);
		
		console2 = new ConsoleBuilder().withManufacturer("Microsoft")
				.withName("XBox one")
				.withNumberOfBits(64)
				.withReleaseDate(new Date())
				.build();
		consoleDao.save(console2);
		
		VideoGame game1 = new VideoGameBuilder().withConsole(console2)
				.withName("Crash Team Racing")
				.withPublisher("Naughty dog")
				.withReleaseDate(new Date())
				.build();
		gameDao.save(game1);
	}
	
	@Test
	public void testDeleteConsoleSuccessful() throws ImpossibleToDeleteException {
		Long id = console1.getId();
		consoleService.deleteConsole(console1.getId());
		
		Optional<Console> console = consoleDao.findById(id);
		assertFalse(console.isPresent());
	}
	
	@Test(expected = ImpossibleToDeleteException.class)
	public void testDeleteConsoleLinkedToGame() throws ImpossibleToDeleteException {
		consoleService.deleteConsole(console2.getId());
	}
}
