package mg.ratombotsoa.gamecollection.service.impl;

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mg.ratombotsoa.gamecollection.model.User;
import mg.ratombotsoa.gamecollection.repository.UserRepository;
import mg.ratombotsoa.gamecollection.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;
	
	@Override
	public User loadUserWithGames(Long userId) {
		User user = userDao.findByIdAndFetchGames(userId);
		user.initSelectedGames();
		return user;
	}

	@Override
	public void saveUser(User user) {
		encryptPassword(user);
		Collection<Long> gameIds = user.getSelectedGameIds();
		userDao.save(user);
		userDao.clearGames(user.getId());
		gameIds.forEach(gameId -> userDao.linkUserGames(gameId, user.getId()));
	}
	
	@Override
	public void deleteUser(Long id) {
		userDao.clearGames(id);
		userDao.deleteById(id);
	}
	
	private void encryptPassword(User user) {
		if (StringUtils.isNotEmpty(user.getTransientPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getTransientPassword()));
		}
	}

}
