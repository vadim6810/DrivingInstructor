package inetex.driver_instructor.dao;

import javax.persistence.*;

@Embeddable
public class Address {
String area;
String city;
String street;

public Address(String area, String city, String street) {
	super();
	this.area = area;
	this.city = city;
	this.street = street;
}
public Address() {
	super();
}

@Override
public String toString() {
	return "Address [area=" + area + ", city=" + city + ", street=" + street + "]";
}

public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}

}
