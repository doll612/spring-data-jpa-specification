package com.techieVibe.learntobefs.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techieVibe.learntobefs.models.Bike;
import com.techieVibe.learntobefs.repositories.BikeRepository;
import com.techieVibe.learntobefs.repositories.spec.SearchCriteria;
import com.techieVibe.learntobefs.repositories.spec.SearchOperator;
import com.techieVibe.learntobefs.repositories.spec.SearchSpecification;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikesController {

	@Autowired
	BikeRepository bikeRepository;

	@GetMapping("/list")
	public List<Bike> list() {
		return bikeRepository.findAll();
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody Bike bike) {
		bikeRepository.save(bike);

	}

	@GetMapping("/{id}")
	public Bike get(@PathVariable("id") long id) {
		return bikeRepository.findById(id).orElse(null);

	}

	@GetMapping("/filter")
	public List<Bike> searchby() {
		SearchSpecification<Bike> bikeSpec = new SearchSpecification();
		bikeSpec.add(new SearchCriteria("name", "Priyanka Singh", SearchOperator.EQUAL));
		bikeSpec.add(new SearchCriteria("purchasePrice", 2100.00, SearchOperator.EQUAL));
		String[] properties = {"name"};
		
		Page<Bike> msTitleRatingList = bikeRepository.findAll(bikeSpec, PageRequest.of(0, 10, Sort.by(Direction.DESC, properties)));
		return msTitleRatingList.toList();
	}
}
