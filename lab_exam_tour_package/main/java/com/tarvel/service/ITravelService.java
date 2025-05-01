package com.tarvel.service;

import java.util.Collection;

import com.tarvel.entity.TourPackage;

public interface ITravelService {
	boolean createTourPackage(TourPackage tourPackage);
	Collection<TourPackage> getAllPackages();
	TourPackage getPackageByName(String name);
	TourPackage getPackageById(Integer id);
	boolean updatePackage(TourPackage tourPackage,String name);
	boolean deletePackage(String name);
}
