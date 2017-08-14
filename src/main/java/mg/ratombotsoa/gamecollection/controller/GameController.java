package mg.ratombotsoa.gamecollection.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;

@Controller
@RequestMapping(value = "/game")
public class GameController {

	private static final String GAME_LIST = "game/game";
	private static final String GAME_DETAIL = "game/gameDetail";
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Autowired
	private ConsoleRepository consoleDao;
	
	@GetMapping(value = "")
	public String getGameList(ModelMap map) {
		List<VideoGame> games = gameDao.findAll(Sort.by(Direction.ASC, "console.name", "releaseDate"));
		map.put("games", games);
		return GAME_LIST;
	}
	
	@GetMapping(value = "/create")
	public String createGame(ModelMap map) {
		map.put("game", new VideoGame());
		map.put("consoles", consoleDao.findAll(Sort.by("name")));
		return GAME_DETAIL;
	}
	
	@PostMapping(value = "/search")
	public String searchGame(ModelMap map, @RequestParam String criteria) {
		List<VideoGame> games = gameDao.findAllByNameContaining(criteria, Sort.by(Direction.ASC, "console.name", "releaseDate"));
		map.put("games", games);
		map.put("criteria", criteria);
		return GAME_LIST;
	}
	
	@GetMapping(value = "/detail/{id}")
	public String goToDetail(ModelMap map, @PathVariable(value = "id", required = true) Long gameId) {
		Optional<VideoGame> game = gameDao.findById(gameId);
		map.put("consoles", consoleDao.findAll(Sort.by("name")));
		map.put("game", game.get());
		return GAME_DETAIL;
	}
	
	@PostMapping(value = "/detail/save")
	public String saveGame(ModelMap map, @ModelAttribute VideoGame game) {
		gameDao.save(game);
		
		List<VideoGame> games = gameDao.findAll(Sort.by(Direction.ASC, "console.name", "releaseDate"));
		map.put("games", games);
		return GAME_LIST;
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteGame(ModelMap map, @PathVariable(value = "id", required = true) Long gameId) {
		gameDao.unlinkGameFromUsers(gameId);
		gameDao.deleteById(gameId);
		List<VideoGame> games = gameDao.findAll(Sort.by(Direction.ASC, "console.name", "releaseDate"));
		map.put("games", games);
		return GAME_LIST;
	}
}
