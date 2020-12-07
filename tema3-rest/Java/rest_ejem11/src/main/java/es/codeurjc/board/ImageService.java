package es.codeurjc.board;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private Path createFilePath(long imageId, Path folder) {
		return folder.resolve("image-" + imageId + ".jpg");
	}
	
	public void saveImage(String folderName, long imageId, MultipartFile image) throws IOException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Files.createDirectories(folder);
		
		Path newFile = createFilePath(imageId, folder);

		image.transferTo(newFile);
	}

	public ResponseEntity<Object> createResponseFromImage(String folderName, long imageId) throws MalformedURLException {

		Path folder = FILES_FOLDER.resolve(folderName);
		
		Path imagePath = createFilePath(imageId, folder);
		
		Resource file = new UrlResource(imagePath.toUri());
		
		if(!Files.exists(imagePath)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
		}		
	}

	public void deleteImage(String folderName, long imageId) throws IOException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Path imageFile = createFilePath(imageId, folder);
		
		Files.deleteIfExists(imageFile);				
	}

}