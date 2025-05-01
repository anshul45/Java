package com.tarvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarvel.entity.TourPackage;


public interface TravelRepository extends JpaRepository<TourPackage, Integer> {
		TourPackage findBypackageName(String packageName);
}
