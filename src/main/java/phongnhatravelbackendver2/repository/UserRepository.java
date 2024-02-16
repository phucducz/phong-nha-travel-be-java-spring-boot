package phongnhatravelbackendver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import phongnhatravelbackendver2.entity.UsersEntity;

@Component
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
	List<UsersEntity> findAll();
	
	UsersEntity findOneById(Long id);
	
	UsersEntity findOneByUserName(String userName);
	
	@Modifying
	@Query(value = "DELETE FROM users WHERE id = :userId", nativeQuery = true)
	int deleteUserById(Long userId);
	
	@Query(value = "SELECT * FROM users WHERE user_name = :userName AND password = :password", nativeQuery = true)
	UsersEntity findOneByUserNameAndPassword(String userName, String password);
	
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	UsersEntity findOneByEmail(String email);
}
