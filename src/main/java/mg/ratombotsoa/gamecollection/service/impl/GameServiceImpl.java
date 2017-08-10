package mg.ratombotsoa.gamecollection.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.service.GameService;
import mg.ratombotsoa.gamecollection.util.SortUtil;

@Service
public class GameServiceImpl implements GameService {

	@Override
	public void sortGames(List<VideoGame> games, Comparator<VideoGame> comparator, boolean ascending) {
		SortUtil.sortCollections(games, comparator, ascending);
	}

}
