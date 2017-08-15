package mg.ratombotsoa.gamecollection.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
	private static final String USER_COLLECTION = "user/collection";
	
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

		boolean isNewUser = user.getId() == null;
		userService.saveUser(user);
		
		if (isNewUser) {
			return "redirect:/login?newuser=true";
		}
		
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
	
	@GetMapping(value = "/collection/{username}")
	public String goToCollection(ModelMap map, @PathVariable(value = "username", required = true) String username) {
		Optional<User> userByName = userDao.findByUsername(username);
		
		if (userByName.isPresent()) {
			User user = userService.loadUserWithGames(userByName.get().getId());
			map.put("user", buildUserLabel(user));
			map.put("games", user.getGames());
			return USER_COLLECTION;
		}
		
		return getUserList(map);
	}
	
	private String buildUserLabel(User user) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(user.getFirstName())) {
			sb.append(user.getFirstName());
			sb.append(" ");
		}
		
		if (StringUtils.isNotEmpty(user.getName())) {
			sb.append(user.getName());
		}
		
		return sb.toString();
	}

	private List<VideoGame> loadGames() {
		return gameDao.findAll(Sort.by("name", "console.name"));
	}
}
