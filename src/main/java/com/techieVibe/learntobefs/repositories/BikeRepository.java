package com.techieVibe.learntobefs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.techieVibe.learntobefs.models.Bike;

public interface BikeRepository
		extends JpaRepository<Bike, Long>, JpaSpecificationExecutor<Bike>, PagingAndSortingRepository<Bike, Long> {

}
