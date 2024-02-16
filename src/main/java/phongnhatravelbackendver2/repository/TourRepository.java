package phongnhatravelbackendver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import phongnhatravelbackendver2.entity.ToursEntity;

public interface TourRepository extends JpaRepository<ToursEntity, Long> {
	ToursEntity findOneById(Long id);

	List<ToursEntity> findAll();

	@Query("SELECT t FROM ToursEntity t, CategoriesEntity e WHERE e.code in :categoryCode")
	List<ToursEntity> findAllByCategoryCode(String categoryCode);
	
	@Query(value = "SELECT t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, "
			+ "t.types_id FROM tours AS t, categories AS c, tour_categories AS tc WHERE t.id = tc.tours_id AND "
			+ "c.id = tc.categories_id AND c.code LIKE CONCAT(CONCAT('%', :categoryCode), '%') AND t.name LIKE CONCAT(CONCAT('%', :tourName), '%') "
			+ "AND t.price_adult BETWEEN :startPrice AND :endPrice AND (t.start_date >= :startDate AND t.end_date <= :endDate)"
			+ "GROUP BY t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, t.types_id "
			+ "ORDER BY id ASC", nativeQuery = true)
	List<ToursEntity> searchTours(String tourName, String categoryCode, String endDate, String startDate, Long startPrice, Long endPrice);
//	@Query(value = "SELECT t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, "
//			+ "t.types_id FROM tours AS t, categories AS c, tour_categories AS tc WHERE t.id = tc.tours_id AND "
//			+ "c.id = tc.categories_id AND c.code LIKE '%-%' AND t.name LIKE '%%' "
//			+ "AND t.price_adult BETWEEN :startPrice AND :endPrice AND (t.start_date = :startDate or t.end_date = :endDate) "
//			+ "GROUP BY t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, t.types_id "
//			+ "ORDER BY id ASC", nativeQuery = true)
//	List<ToursEntity> searchTours(String endDate, String startDate, Long startPrice, Long endPrice);
//	@Query(value = "SELECT t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, "
//			+ "t.types_id FROM tours AS t, categories AS c, tour_categories AS tc WHERE t.id = tc.tours_id AND "
//			+ "c.id = tc.categories_id AND c.code LIKE '%-%' AND t.name LIKE '%%' AND t.price_adult BETWEEN 0 AND 1675969 "
//			+ "GROUP BY t.id, t.description, t.end_date, t.name, t.price_adult, t.price_children, t.start_date, t.types_id "
//			+ "ORDER BY id ASC", nativeQuery = true)
//	List<ToursEntity> searchTours();
}
