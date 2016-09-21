package inetex.driver_instructor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import inetex.driver_instructor.dao.Address;
import inetex.driver_instructor.implementation.InstructorHiber;

public class CreateInstructor {
	static int nInstructors=10;
	static int nNames=30;
	static int nAreas = 5;
	static int nStreets=40;
	static int nCities=30;
	static String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
	static String[] hours = {"9.00 - 13.30; 14.30 - 18.00","10.00 - 14.00; 15.00 - 19.00","8.30 - 13.00"};
	
	static void createInstructors(InstructorHiber instructDB){
		int id = 1;
		while(id<=nInstructors)
			if(createInstructor(instructDB)) setWorkingDays(id++,instructDB);
	}
	
	static void removeInstructors(InstructorHiber instructDB){
		for(int id=1;id<=nInstructors;id++) {
			String email = instructDB.getInstructorById(id).getEmail();
			instructDB.removeInstructor(email);
		}
	}
	
	static boolean createInstructor(InstructorHiber instructDB) {
		String name="name"+getRandomInteger(1,nNames);
		String email = name+"@yahoo.com";
		Set<String> phones = new TreeSet<String>();
		phones.add("050-652-88"+getRandomInteger(10,99));
		String sex, typeVehicle, transmission;
		if(Math.random()<0.5) sex = "m";
		else sex = "f";
		Address address=getAddress();
		typeVehicle = setTypeVehicle();
		if(Math.random()<0.5) transmission = "auto";
		else transmission = "mech";
		return instructDB.addInstructor(name, email, phones, sex, address, typeVehicle, transmission);
	}

	private static String setTypeVehicle() {
		double flip = Math.random();
		String type="";
		if(flip<0.25) type = "A";
		if(flip>=0.25 && flip<0.5) type = "B";
		if(flip>=0.5 && flip<0.75) type = "C";
		if(flip>=0.75 && flip<1.) type = "D";
		return type;
	}
	
	private static Address getAddress() {
		String area="area"+getRandomInteger(1, nAreas);
		String city="city"+getRandomInteger(1, nCities);
		String street="street"+getRandomInteger(1,nStreets);
		return new Address(area, city, street);
	}
	
	static void setWorkingDays(int idInst, InstructorHiber instructDB){
		List<String> workDays = new ArrayList<String>();
		for(int j=0;j<getRandomInteger(0,days.length);j++) workDays.add(days[j]);
		instructDB.setWorkingDays(idInst, workDays);
		instructDB.setWorkingHours(idInst, setWorkingHours(workDays));
	}
	
	private static List<String> setWorkingHours(List<String> workDays) {
		List<String> workHours = new ArrayList<String>();
		for(String day:workDays){
			if(!day.equals("Friday")) workHours.add(hours[getRandomInteger(0,1)]);
			else workHours.add(hours[2]);
		}
		return workHours;
	}
	
	private static int getRandomInteger(int min, int max) {
		return min+(int) (Math.random()*(max-min+1));
	}
	
}









