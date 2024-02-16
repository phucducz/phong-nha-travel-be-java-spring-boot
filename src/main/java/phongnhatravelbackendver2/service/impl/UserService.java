package phongnhatravelbackendver2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import phongnhatravelbackendver2.converter.UserConverter;
import phongnhatravelbackendver2.dto.Login;
import phongnhatravelbackendver2.dto.UsersDTO;
import phongnhatravelbackendver2.entity.UsersEntity;
import phongnhatravelbackendver2.repository.UserRepository;
import phongnhatravelbackendver2.service.IUserService;
import phongnhatravelbackendver2.service.StorageService;

@Service
public class UserService implements IUserService {
	private final StorageService storageService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	public UserService(StorageService storageService) {
		this.storageService = storageService;
		storageService.init();
	}

	public List<UsersDTO> getUserById(Long id, String type) {
		List<UsersEntity> listEntity = new ArrayList<UsersEntity>();

		if (id != null)
			listEntity.add(userRepository.findOneById(id));
		else
			listEntity = userRepository.findAll();

		return userConverter.toListDTO(listEntity, type);
	}

	public UsersEntity getUserEntityById(Long id) {
		return userRepository.findOneById(id);
	}

	@Override
//	public boolean save(UsersDTO userDTO, MultipartFile file) {
	public boolean save(UsersDTO userDTO) {
		UsersEntity entity = new UsersEntity();

		if (userDTO.getId() != null) {
			entity = userRepository.findOneById(userDTO.getId());

			UsersEntity newEntity = userConverter.toEntity(userDTO);

			entity = userConverter.toNewEntity(entity, newEntity);
		} else
			entity = userConverter.toEntity(userDTO);

		entity = userRepository.save(entity);

//		if (file != null)
//			try {
//				storageService.store(file, userDTO.getFileName());
//
//				if (entity.getId() != null)
//					return true;
//			} catch (Exception ex) {
//				System.out.print(ex);
//				return false;
//			}

		if (entity.getId() != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		try {
			UsersEntity entity = userRepository.findOneById(id);

			if (userRepository.deleteUserById(id) > 0)
				storageService.deleteFile(entity.getAvatar());

			return true;
		} catch (Exception ex) {
			System.out.print(ex);
		}

		return false;
	}

	@Override
	public UsersEntity login(Login login) {
		return userRepository.findOneByUserNameAndPassword(login.getUserName(), login.getPassword());
	}
}
