package com.tarvel.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tarvel.entity.TourPackage;

@RestController
public class TravelController {
	@Autowired
	ITravelService travelService;

	//To add
	@PostMapping("/add")
	public ResponseEntity<?> addtourPackage(@RequestBody TourPackage tourPackage) {
		try {
			boolean status = travelService.createTourPackage(tourPackage);
			if (status) {
				return new ResponseEntity<>("Package Saved Sucessfully!", HttpStatus.OK);
			} else
				return new ResponseEntity<>("Failed to Save Package!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	 // to get all.
	@GetMapping("/tours")
	public ResponseEntity<?> getAllPackages() {
		try {			
			Collection<TourPackage> tourPackages = travelService.getAllPackages();
			return new ResponseEntity<>(tourPackages, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
			//NO_CONTENT not send any response. it only send status code.
			//We can use NOT_FOUND also instead of this.
		}
	}

	
	//For get by name
	@GetMapping("/tours/name/{name}")
	public ResponseEntity<?> getPackageByName(@PathVariable String name) {
		try {
			TourPackage tourPackage = travelService.getPackageByName(name);
			return new ResponseEntity<>(tourPackage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
//For get by id
	@GetMapping("/tours/{id}")
	public ResponseEntity<?> getPackageById(@PathVariable Integer id) {
		try {
			TourPackage tourPackage = travelService.getPackageById(id);
			return new ResponseEntity<>(tourPackage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	//For Updating by name
	@PutMapping("/tours/{name}")
	public ResponseEntity<?> updatePackage(@PathVariable String name, @RequestBody TourPackage tourPackage) {
		try {
			boolean status =  travelService.updatePackage(tourPackage,name);
			if (status) {
				return new ResponseEntity<>("Package Updated Sucessfully!", HttpStatus.OK);
			} else
				return new ResponseEntity<>("Failed to Update Package!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	//For deleting by name
	@DeleteMapping("/tours/{name}")
	public ResponseEntity<?> deletePackage(@PathVariable String name) {
		try {
			boolean status =  travelService.deletePackage(name);
			if (status) {
				return new ResponseEntity<>("Package Deleted Sucessfully!", HttpStatus.OK);
			} else
				return new ResponseEntity<>("Failed to Delete Package!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
