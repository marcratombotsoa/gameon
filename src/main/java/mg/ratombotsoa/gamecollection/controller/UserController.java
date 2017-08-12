package mg.ratombotsoa.gamecollection.controller;

import java.util.List;
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

import mg.ratombotsoa.gamecollection.model.User;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.repository.UserRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;
import mg.ratombotsoa.gamecollection.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final String USER_LIST = "user/user";
	private static final String USER_DETAIL = "user/userDetail";
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public String getUserList(ModelMap map) {
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
	
	@GetMapping(value = "/create")
	public String createUser(ModelMap map) {
		map.put("user", new User());
		map.put("allGames", loadGames());
		return USER_DETAIL;
	}
	
	@GetMapping(value = "/detail/{id}")
	public String goToDetail(ModelMap map, @PathVariable(value = "id", required = true) Long userId) {
		User user = userService.loadUserWithGames(userId);
		map.put("user", user);
		map.put("allGames", loadGames());
		return USER_DETAIL;
	}
	
	@PostMapping(value = "/detail/save")
	public String saveUser(ModelMap map, @ModelAttribute User user) {
		userService.saveUser(user);
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteUser(ModelMap map, @PathVariable(value = "id", required = true) Long userId) {
		userService.deleteUser(userId);
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
	
	private List<VideoGame> loadGames() {
		return gameDao.findAll(Sort.by("name", "console.name"));
	}
}
