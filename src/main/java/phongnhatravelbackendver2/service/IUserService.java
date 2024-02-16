package phongnhatravelbackendver2.service;

import java.util.List;

import phongnhatravelbackendver2.dto.Login;
import phongnhatravelbackendver2.dto.UsersDTO;
import phongnhatravelbackendver2.entity.UsersEntity;

public interface IUserService {
	List<UsersDTO> getUserById(Long id, String type);
	
	UsersEntity getUserEntityById(Long id);
	
//	boolean save(UsersDTO userDTO, MultipartFile file);
	boolean save(UsersDTO userDTO);
	
	boolean delete(Long id);
	
	UsersEntity login(Login login);
}
