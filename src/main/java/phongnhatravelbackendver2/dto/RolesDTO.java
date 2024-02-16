package phongnhatravelbackendver2.dto;

import java.util.List;

import phongnhatravelbackendver2.entity.UsersEntity;

public class RolesDTO {
	private Long id;
	private String role;
	
	private List<UsersEntity> listUser;

	public List<UsersEntity> getListUser() {
		return listUser;
	}

	public void setListUser(List<UsersEntity> listUser) {
		this.listUser = listUser;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}
}