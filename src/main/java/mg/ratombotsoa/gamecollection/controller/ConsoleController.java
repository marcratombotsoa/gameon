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

import mg.ratombotsoa.gamecollection.exception.ImpossibleToDeleteException;
import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.service.ConsoleService;

@Controller
@RequestMapping(value = "/console")
public class ConsoleController {

	@Autowired
	private ConsoleRepository consoleDao;
	
	@Autowired
	private ConsoleService consoleService;
	
	@GetMapping(value = "")
	public String getConsoleList(ModelMap map) {
		List<Console> consoles = consoleDao.findAll(Sort.by(Direction.DESC, "releaseDate"));
		map.put("consoles", consoles);
		return "console";
	}
	
	@PostMapping(value = "/search")
	public String searchConsole(ModelMap map, @RequestParam String criteria) {
		List<Console> consoles = consoleDao.findAllByNameContaining(criteria);
		map.put("consoles", consoles);
		map.put("criteria", criteria);
		return "console";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String goToDetail(ModelMap map, @PathVariable(value = "id", required = true) Long consoleId) {
		Optional<Console> console = consoleDao.findById(consoleId);
		map.put("console", console.get());
		return "consoleDetail";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteConsole(ModelMap map, @PathVariable(value = "id", required = true) Long consoleId) {
		try {
			consoleService.deleteConsole(consoleId);
		} catch (ImpossibleToDeleteException e) {
			map.put("errorMessage", e.getMessage());
		}
		
		List<Console> consoles = consoleDao.findAll(Sort.by(Direction.DESC, "releaseDate"));
		map.put("consoles", consoles);
		return "console";
	}
	
	@GetMapping(value = "/create")
	public String createConsole(ModelMap map) {
		map.put("console", new Console());
		return "consoleDetail";
	}
	
	@PostMapping(value = "/detail/save")
	public String saveConsole(ModelMap map, @ModelAttribute Console console) {
		consoleDao.save(console);
		
		List<Console> consoles = consoleDao.findAll(Sort.by(Direction.DESC, "releaseDate"));
		map.put("consoles", consoles);
		return "console";
	}
}
