package mg.ratombotsoa.gamecollection.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mg.ratombotsoa.gamecollection.repository.VideoGameRepository;
import mg.ratombotsoa.gamecollection.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	private static final String PHOTO_ROOT_FOLDER = "gameon";
	private static final String PHOTO_EXTENSION = ".jpg";
	
	@Autowired
	private VideoGameRepository gameDao;
	
	@Override
	public void storeCover(MultipartFile file, Long gameId) throws IOException {
		File rootFolder = getPhotoFolder();
		byte[] contents = file.getBytes();
		
		Path path = Paths.get(rootFolder.getAbsolutePath(), String.valueOf(gameId) + PHOTO_EXTENSION);
		File destinationFile = new File(path.toAbsolutePath().toString());
		if (!destinationFile.exists()) {
			destinationFile.createNewFile();
		}
		Files.write(path, contents, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	@Override
	public byte[] getImage(Long gameId) throws IOException {
		File imageFile = getImageFile(gameId);
	    return Files.readAllBytes(imageFile.toPath());
	}
	
	private File getPhotoFolder() throws IOException {
		String tmp = System.getProperty("java.io.tmpdir");
		File folder = new File(tmp, PHOTO_ROOT_FOLDER);
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		}
		
		return folder;
	}
	
	private File getImageFile(Long gameId) throws IOException {
		return new File(getPhotoFolder(), gameId + PHOTO_EXTENSION);
	}

	@Override
	public void deleteGameCover(Long gameId) throws IOException {
		File imageFile = getImageFile(gameId);
		if (imageFile.exists()) {
			Files.delete(Paths.get(imageFile.getAbsolutePath()));
		}
	}

	@Override
	public void deleteGame(Long gameId) throws IOException {
		gameDao.unlinkGameFromUsers(gameId);
		gameDao.deleteById(gameId);
		
		deleteGameCover(gameId);
	}

}
