package phongnhatravelbackendver2.service;

import phongnhatravelbackendver2.dto.CartDTO;
import phongnhatravelbackendver2.entity.CartEntity;

public interface ICartService {
	boolean updateCartQuantityById(int quantity, Long id);
	
	boolean deleteCartById(Long id);
	
	boolean save(CartDTO cart);
	
	boolean updateCheckoutIdByUserId(Long userId, Long checkoutsId);
	
	CartEntity findItemOfCart(Long tourId, Long userId);
}
