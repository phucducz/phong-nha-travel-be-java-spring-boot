package phongnhatravelbackendver2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import phongnhatravelbackendver2.service.StorageService;

@CrossOrigin
@RestController
public class FileUploadController {
	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
		storageService.init();
	}

	@PostMapping("/files")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("fileName") String fileName,
			RedirectAttributes redirectAttributes) {
		storageService.store(file, fileName);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

}