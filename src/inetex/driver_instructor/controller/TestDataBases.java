package inetex.driver_instructor.controller;

import java.util.*;

import org.hibernate.Hibernate;

import inetex.driver_instructor.implementation.InstructorHiber;
import inetex.driver_instructor.implementation.ScheduleHiber;
import inetex.driver_instructor.dao.*;
import inetex.driver_instructor.implementation.*;

public class TestDataBases {

	public static void testDB(InstructorHiber instructDB, ScheduleHiber schedDB){
		// --- Testing Instructor Hibernate -------
		showInstructorResult("By vehicle type:",instructDB.getInstructorByTypeVehicle("B"));
		showInstructorResult("By transmission:",instructDB.getInstructorByTransmission("mech"));
		showInstructorResult("By sex:",instructDB.getInstructorBySex("f"));
		showInstructorResult("By area:",instructDB.getInstructorByArea("area4"));
		showInstructorResult("By city:",instructDB.getInstructorByCity("area4", "city25"));
		changeEmail(instructDB);
		phones(instructDB);
		changeAddress(instructDB);
		changeVehicle(instructDB);
		workingDaysHours(instructDB);
		changeURL(instructDB);
		
		// --- Testing Schedule Hibernate -------
		getScheduleByInstructor(instructDB, schedDB);
		getScheduleByClient(schedDB);
	}
	
	private static void workingDaysHours(InstructorHiber instructDB) {
		int id = 6;
		System.out.println(" ");
		System.out.println("Changing working days/hours...");
		showStringResult("Current working days: ",instructDB.getWorkingDays(id));
		showStringResult("Current working hours: ",instructDB.getWorkingHours(id));
		List<String> workDH = new ArrayList<String>();
		for(int i=0;i<4;i++) workDH.add(i,"day "+i);
		instructDB.setWorkingDays(id, workDH);
		for(int i=0;i<4;i++) workDH.set(i,"hour "+i);
		instructDB.setWorkingHours(id, workDH);
		showStringResult("Updated working days: ",instructDB.getWorkingDays(id));
		showStringResult("Updated working hours: ",instructDB.getWorkingHours(id));
	}

	private static void getScheduleByClient(ScheduleHiber schedDB) {
		int idClient = 27;
		System.out.println(" ");
		List<Schedule> items = schedDB.getScheduleByClient(idClient);
		showScheduleResult("Getting schedule by client...",items);
		System.out.println("Changing first item...");
		int idInst = items.get(0).getInstructor().getId();
		Date date = items.get(0).getDate();
		Date newDate = new GregorianCalendar(2016, 8, 17, 6, 45).getTime();
		schedDB.changeItemSchedule(idInst, idClient, date, newDate, "23min", "Best place", "It's Ok!", false);
		items = schedDB.getScheduleByClient(idClient);
		showScheduleResult("The same schedule after changing first item...",items);
	}

	private static void getScheduleByInstructor(InstructorHiber instructDB, ScheduleHiber schedDB) {
		int id = 9;
		System.out.println(" ");
		Date date = new GregorianCalendar(2016, 8, 5, 17, 0).getTime();
		System.out.println("Getting item for instructor and date...");
		System.out.println(schedDB.getItemSchedule(id, date));
		List<Schedule> items = schedDB.getScheduleByInstructor(id);
		showScheduleResult("Getting schedule by instructor...",items);
		System.out.println("Getting first item from this schedule...");
		System.out.println(schedDB.getItemSchedule(id, items.get(0).getDate()));
		System.out.println("Removing this first schedule item...");
		schedDB.removeItemSchedule(id, items.get(0).getDate());
		items = schedDB.getScheduleByInstructor(id);
		showScheduleResult("The same schedule after this removal...",items);
	}

	private static void changeURL(InstructorHiber instructDB) {
		int id = 4;
		System.out.println(" ");
		System.out.println("Changing URL...");
		Instructor inst = instructDB.getInstructorById(id);
		System.out.println(inst.getUrl());
		instructDB.changeURL(id, "http://www.yahoo7.com");
		System.out.println("The same instructor with the new URL:");
		inst = instructDB.getInstructorById(id);
		System.out.println(inst.getUrl());
	}

	private static void changeVehicle(InstructorHiber instructDB) {
		int id = 3;
		System.out.println(" ");
		System.out.println("Changing vehicle parameters...");
		Instructor inst = instructDB.getInstructorById(id);
		System.out.println("Vehicle type "+inst.getTypeVehicle()+"; transm "+inst.getTransmission());
		instructDB.changeVehicle(id, "B", "auto");
		System.out.println("The same instructor with the new vehicle:");
		inst = instructDB.getInstructorById(id);
		System.out.println("Vehicle type "+inst.getTypeVehicle()+"; transm "+inst.getTransmission());
	}

	private static void changeAddress(InstructorHiber instructDB) {
		int id = 8;
		System.out.println(" ");
		System.out.println("Changing address...");
		Address adr = new Address("area2","city8","street31");
		System.out.println(instructDB.getInstructorById(id).getAddress());
		instructDB.changeAddress(id, adr);
		System.out.println("The same instructor with the new address:");
		System.out.println(instructDB.getInstructorById(id).getAddress());
	}

	private static void changeEmail(InstructorHiber instructDB) {
		int id = 5;
		String email = instructDB.getInstructorById(id).getEmail();
		String newEmail = "name16@yahoo.com";
		System.out.println(" ");
		System.out.println("Changing email "+email+" to "+newEmail);
		if(instructDB.changeEmail(id, newEmail)) 
			System.out.println(instructDB.getInstructorById(id).getEmail());
		else System.out.println("No such instructor or such email already exists in DB.");
	}

	private static void phones(InstructorHiber instructDB){
		int id = 5;
		showStringResult("Current set of phones:",instructDB.getPhones(id));
		instructDB.addPhone(id, "052-334-9981");
		showStringResult("After adding phone:",instructDB.getPhones(id));
		instructDB.removePhone(id, "052-334-9981");
		showStringResult("After removing phone:",instructDB.getPhones(id));
	}
	
	private static void showStringResult(String title, Collection<String> result){
		System.out.println(" ");
		System.out.println(title);
		if(result!=null)
			for(String str:result) System.out.println(str);
	}
	
	private static void showInstructorResult(String title, List<Instructor> result){
		System.out.println(" ");
		System.out.println(title);
		if(result!=null)
			for(Instructor inst:result) System.out.println(inst.toString());
	}
	
	private static void showScheduleResult(String title, List<Schedule> result){
		System.out.println(" ");
		System.out.println(title);
		if(result!=null)
			for(Schedule sched:result) System.out.println(sched.toString());
	}
}












