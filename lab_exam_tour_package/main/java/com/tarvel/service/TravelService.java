package com.tarvel.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tarvel.entity.TourPackage;
import com.tarvel.exception.ResourceAlreadyAvailableException;
import com.tarvel.exception.ResourceNotFoundException;
import com.tarvel.repository.TravelRepository;

@Service
public class TravelService implements ITravelService {
	@Autowired
	TravelRepository travelRepository;

	@Override
	public boolean createTourPackage(TourPackage tourPackage) {
		TourPackage isPackageExist = travelRepository.findBypackageName(tourPackage.getPackageName());
		if (!ObjectUtils.isEmpty(isPackageExist))
			throw new ResourceAlreadyAvailableException(
					"Resource already available with name: " + tourPackage.getPackageName());
		TourPackage isTourPackageSaved = travelRepository.save(tourPackage);
		if (ObjectUtils.isEmpty(isTourPackageSaved))
			return false;
		else
			return true;
	}

	@Override
	public Collection<TourPackage> getAllPackages() {
		Collection<TourPackage> packages = travelRepository.findAll();
		if(ObjectUtils.isEmpty(packages)) throw new NoDataException("Sorry No data in Database!!");
		return packages;
	}

	@Override
	public TourPackage getPackageByName(String name) {
		TourPackage tourPackage = travelRepository.findBypackageName(name);
		if (tourPackage == null)
			throw new ResourceNotFoundException("Travel Package not found on name: " + name);
		else
			return tourPackage;
	}

	@Override
	public TourPackage getPackageById(Integer id) {
		return travelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Travel Package not found on id: " + id));
	}

	@Override
	public boolean updatePackage(TourPackage tourPackage, String name) {
		TourPackage isPackageExist = travelRepository.findBypackageName(name);
		if (ObjectUtils.isEmpty(isPackageExist))
			throw new ResourceNotFoundException("Resource not available with name: " + name);
		isPackageExist.setPackageName(tourPackage.getPackageName());
		isPackageExist.setDuration(tourPackage.getDuration());
		TourPackage isTourPackageSaved = travelRepository.save(isPackageExist);
		if (ObjectUtils.isEmpty(isTourPackageSaved))
			return false;
		else
			return true;
	}

	@Override
	public boolean deletePackage(String name) {
		TourPackage isPackageExist = travelRepository.findBypackageName(name);
		if (ObjectUtils.isEmpty(isPackageExist))
			throw new ResourceNotFoundException("Resource not available with name: " + name);
		travelRepository.delete(isPackageExist);
		return true;
	}

}
