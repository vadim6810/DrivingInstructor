package inetex.driver_instructor.controller;

import org.springframework.context.support.*;

import inetex.driver_instructor.implementation.InstructorHiber;
import inetex.driver_instructor.implementation.ScheduleHiber;
import inetex.driver_instructor.implementation.*;

public class DBCreaterAppl {

	public static void main(String[] args) {
		AbstractApplicationContext ctx=new FileSystemXmlApplicationContext("beans.xml");
		InstructorHiber instructDB=(InstructorHiber) ctx.getBean("instructDB");
		ScheduleHiber schedDB = (ScheduleHiber) ctx.getBean("scheduleDB");

		// ----  Creating databases  ------
		CreateInstructor.createInstructors(instructDB);
		CreateScheduleItem.createSchedule(schedDB);
		
		// ----  Testing databases  -------
		//TestDataBases.testDB(instructDB, schedDB);
		
		// ---  Deleting databases  -------
		//CreateInstructor.removeInstructors(instructDB);
		ctx.close();
	}

}












