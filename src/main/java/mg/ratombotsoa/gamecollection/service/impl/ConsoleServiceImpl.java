package mg.ratombotsoa.gamecollection.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.ratombotsoa.gamecollection.exception.ImpossibleToDeleteException;
import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.repository.ConsoleRepository;
import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;
import mg.ratombotsoa.gamecollection.service.ConsoleService;
import mg.ratombotsoa.gamecollection.util.SortUtil;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	@Autowired
	private ConsoleRepository consoleDao;
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Override
	public void sortConsoles(List<Console> consoles, Comparator<Console> comparator,
			boolean ascending) {
		SortUtil.sortCollections(consoles, comparator, ascending);
	}

	@Override
	public void deleteConsole(Long id) throws ImpossibleToDeleteException {
		List<VideoGame> games = gameDao.findAllByConsoleId(id);
		if (!games.isEmpty()) {
			throw new ImpossibleToDeleteException("This console is still linked to " + games.size() + " games");
		}
		consoleDao.deleteById(id);
	}

}
