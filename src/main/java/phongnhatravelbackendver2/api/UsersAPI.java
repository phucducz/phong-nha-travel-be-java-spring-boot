package phongnhatravelbackendver2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import phongnhatravelbackendver2.dto.Login;
import phongnhatravelbackendver2.dto.MessageDTO;
import phongnhatravelbackendver2.dto.UsersDTO;
import phongnhatravelbackendver2.entity.UsersEntity;
import phongnhatravelbackendver2.repository.UserRepository;
import phongnhatravelbackendver2.service.impl.UserService;

@CrossOrigin
@RestController
public class UsersAPI {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UsersDTO> getUser(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "type", required = false) String type) {
		return userService.getUserById(id, type);
	}

	@PostMapping("/users")
	public MessageDTO createUser(@ModelAttribute UsersDTO userDTO) {
		MessageDTO message = new MessageDTO();

		if (userDTO.getId() == null && (userRepository.findOneByEmail(userDTO.getEmail()) != null
				|| userRepository.findOneByUserName(userDTO.getUserName()) != null)) {
			message.setMessage("email or userName already exist!");
			message.setStatus(409);

			return message;
		}

//		boolean result = userService.save(userDTO, file);
		boolean result = userService.save(userDTO);

		if (result) {
			message.setMessage("Action successfully!");
			message.setStatus(200);
		} else {
			message.setMessage("Internal server error, action failure!");
			message.setStatus(500);
		}

		return message;
	}

	@PostMapping("/users/login")
	public UsersEntity login(@RequestBody Login loginModel) {
		return userService.login(loginModel);
	}

	@DeleteMapping("/users")
	public MessageDTO deleteUser(@RequestParam("id") Long id) {
		boolean result = userService.delete(id);

		MessageDTO message = new MessageDTO();

		if (result) {
			message.setMessage("Delete successfully!");
			message.setStatus(200);
		} else {
			message.setMessage("Internal server error, delete failure!");
			message.setStatus(200);
		}

		return message;
	}

	@PutMapping("/users")
	public MessageDTO updateUser(@ModelAttribute UsersDTO userDTO,
			@RequestParam(name = "file", required = false) MultipartFile file) {
//		boolean result = userService.save(userDTO, file);
		boolean result = userService.save(userDTO);

		MessageDTO message = new MessageDTO();

		if (result) {
			message.setMessage("Update successfully!");
			message.setStatus(200);
		} else {
			message.setMessage("Internal server error, update failure!");
			message.setStatus(200);
		}

		return message;
	}
}
