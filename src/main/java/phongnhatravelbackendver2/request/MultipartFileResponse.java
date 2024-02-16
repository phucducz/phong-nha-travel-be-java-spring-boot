package phongnhatravelbackendver2.request;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileResponse {
	MultipartFile file;
	
	public MultipartFileResponse() {
		super();
	}

	public MultipartFileResponse(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
