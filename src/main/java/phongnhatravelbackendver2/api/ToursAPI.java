package phongnhatravelbackendver2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import phongnhatravelbackendver2.dto.ToursDTO;
import phongnhatravelbackendver2.service.impl.TourService;

@CrossOrigin
@RestController
public class ToursAPI {
	@Autowired
	private TourService tourService;

	@GetMapping("/tours")
	public List<ToursDTO> getTour(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "categoryCode", required = false) String categoryCode) {
		if (id != null)
			return tourService.getTour(id);
		else if (categoryCode != null)
			return null;

		return tourService.getListTour();
	}

	@GetMapping("tours/search")
	public List<ToursDTO> searchTours(@RequestParam(name = "code", required = false) String categoryCode,
			@RequestParam(name = "name", required = false) String tourName,
			@RequestParam(name = "startPrice", required = false) Long startPrice,
			@RequestParam(name = "endPrice", required = false) Long endPrice,
			@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate) {
		return tourService.searchTours(tourName, categoryCode, endDate, startDate, startPrice, endPrice);
	}

	@PostMapping("/tours")
	public ToursDTO createTour(@RequestBody ToursDTO tourModel) {
		return tourService.save(tourModel);
	}

	@PutMapping("/tours")
	public ToursDTO putTour(@RequestBody ToursDTO model) {
		return tourService.save(model);
	}

	@DeleteMapping("/tours/{id}")
	public Long deleteTour(@PathVariable Long id) {
		return id;
	}
}
