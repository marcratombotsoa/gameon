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

import mg.ratombotsoa.gamecollection.model.User;
import mg.ratombotsoa.gamecollection.repository.UserRepository;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final String USER_LIST = "user/user";
	private static final String USER_DETAIL = "user/userDetail";
	
	@Autowired
	private UserRepository userDao;
	
	@GetMapping(value = "")
	public String getUserList(ModelMap map) {
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
	
	@GetMapping(value = "/create")
	public String createGame(ModelMap map) {
		map.put("user", new User());
		return USER_DETAIL;
	}
	
	@GetMapping(value = "/detail/{id}")
	public String goToDetail(ModelMap map, @PathVariable(value = "id", required = true) Long userId) {
		Optional<User> user = userDao.findById(userId);
		map.put("user", user.get());
		return USER_DETAIL;
	}
	
	@PostMapping(value = "/detail/save")
	public String saveGame(ModelMap map, @ModelAttribute Object user) {
		
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteGame(ModelMap map, @PathVariable(value = "id", required = true) Long userId) {
		userDao.deleteById(userId);
		List<User> users = userDao.findAll(Sort.by(Direction.ASC, "name", "firstName"));
		map.put("users", users);
		return USER_LIST;
	}
}
