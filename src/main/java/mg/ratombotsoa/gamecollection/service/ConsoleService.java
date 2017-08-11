package mg.ratombotsoa.gamecollection.service;

import java.util.Comparator;
import java.util.List;

import mg.ratombotsoa.gamecollection.exception.ImpossibleToDeleteException;
import mg.ratombotsoa.gamecollection.model.Console;

public interface ConsoleService {

	/**
	 * Sort the list of consoles by using the specified comparator
	 * 
	 * @param consoles
	 * @param comparator
	 */
	void sortConsoles(List<Console> consoles, Comparator<Console> comparator, boolean ascending);
	
	void deleteConsole(Long id) throws ImpossibleToDeleteException;
}
