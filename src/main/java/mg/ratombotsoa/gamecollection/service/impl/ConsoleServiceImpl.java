package mg.ratombotsoa.gamecollection.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.service.ConsoleService;
import mg.ratombotsoa.gamecollection.util.SortUtil;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	@Override
	public void sortConsoles(List<Console> consoles, Comparator<Console> comparator,
			boolean ascending) {
		SortUtil.sortCollections(consoles, comparator, ascending);
	}

}
