package phongnhatravelbackendver2.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void init();

	void store(MultipartFile file, String fileName);

	void deleteFile(String fileName);
}