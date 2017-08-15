package mg.ratombotsoa.gamecollection.service;

import mg.ratombotsoa.gamecollection.exception.ImpossibleToDeleteException;

public interface ConsoleService {

	void deleteConsole(Long id) throws ImpossibleToDeleteException;
}
