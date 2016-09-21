package inetex.driver_instructor.controller;

import java.util.Date;
import java.util.GregorianCalendar;

import inetex.driver_instructor.implementation.ScheduleHiber;

public class CreateScheduleItem {
	static int nInstructors=10;
	static int nClients=30;
	static int nStreets=40;
	static int nBuildings=20;
	static int nCities=30;

	static void createSchedule(ScheduleHiber schedDB){
		for(int i=0; i<nClients; i++)
			createScheduleItem(schedDB);
	}
	
	static boolean createScheduleItem(ScheduleHiber schedDB) {
		int idInst = getRandomNumber(1,nInstructors);
		int idClient = getRandomNumber(1,nClients);
		String interv = "30 min";
		return schedDB.addItemSchedule(idInst, idClient, getDate(), interv, getPlace());
	}
	
	private static Date getDate() {
		int day = getRandomNumber(1,31);
		int hour = getRandomNumber(9,18);
		int min;
		if(getRandomNumber(0,1)<0.5) min = 0;
		else min = 30;
		return new GregorianCalendar(2016, 8, day, hour, min).getTime();
	}
	
	private static String getPlace() {
		String city="city"+getRandomNumber(1, nCities);
		String street="street"+getRandomNumber(1,nStreets);
		String bld="bld"+getRandomNumber(1, nBuildings);
		return city+", "+street+", "+bld;
	}
	
	private static int getRandomNumber(int min, int max) {
		return min+(int) (Math.random()*(max-min+1));
	}
}







