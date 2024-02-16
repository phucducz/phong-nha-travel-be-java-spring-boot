package phongnhatravelbackendver2.service;

import java.util.List;

import phongnhatravelbackendver2.dto.RolesDTO;
import phongnhatravelbackendver2.entity.RolesEntity;

public interface IRoleService {
	public List<RolesDTO> getListRole();
	
	public RolesEntity getRoleById(Long id);
}
