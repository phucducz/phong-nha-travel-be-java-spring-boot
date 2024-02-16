package phongnhatravelbackendver2.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import phongnhatravelbackendver2.properties.StorageException;
import phongnhatravelbackendver2.properties.StorageProperties;
import phongnhatravelbackendver2.service.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {

		if (properties.getLocation().trim().length() == 0) {
			throw new StorageException("File upload location can not be Empty.");
		}

		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String fileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			
			Path destinationFilePublic = this.rootLocation
					.resolve(Paths.get("D:\\ReactJs\\phong-nha-travel-frontend-reactjs\\public\\images\\" + fileName));
			
			Path destinationFile = this.rootLocation
					.resolve(Paths.get("D:\\ReactJs\\phong-nha-travel-frontend-reactjs\\src\\images\\" + fileName))
					.normalize().toAbsolutePath();
			
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePublic, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		try {
			FileSystemUtils.deleteRecursively(Paths.get("D:\\ReactJs\\phong-nha-travel-frontend-reactjs\\public\\images\\" + fileName));
			FileSystemUtils.deleteRecursively(Paths.get("D:\\ReactJs\\phong-nha-travel-frontend-reactjs\\src\\images\\" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}