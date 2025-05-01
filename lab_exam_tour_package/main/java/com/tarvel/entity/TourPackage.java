package com.tarvel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TourPackage {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer packageId;

@Column(length = 30)
private String packageName;
@Column(length = 30)
private Integer duration;
private Integer price;
@Enumerated(EnumType.STRING)
private TransportationType  transportationType;

public TourPackage() {
}



public TourPackage(Integer packageId, String packageName, Integer duration, Integer price,
		TransportationType transportationType) {
	this.packageId = packageId;
	this.packageName = packageName;
	this.duration = duration;
	this.price = price;
	this.transportationType = transportationType;
}



public Integer getPackageId() {
	return packageId;
}

public void setPackageId(Integer packageId) {
	this.packageId = packageId;
}

public String getPackageName() {
	return packageName;
}

public void setPackageName(String packageName) {
	this.packageName = packageName;
}

public Integer getDuration() {
	return duration;
}

public void setDuration(Integer duration) {
	this.duration = duration;
}

public Integer getPrice() {
	return price;
}

public void setPrice(Integer price) {
	this.price = price;
}

public TransportationType getTransportationType() {
	return transportationType;
}

public void setTransportationType(TransportationType transportationType) {
	this.transportationType = transportationType;
}

@Override
public String toString() {
	return "TourPackage [packageId=" + packageId + ", packageName=" + packageName + ", duration=" + duration
			+ ", price=" + price + ", transportationType=" + transportationType + "]";
}


}
