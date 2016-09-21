package inetex.driver_instructor.implementation;

import java.util.*;

import javax.persistence.*;

import org.springframework.transaction.annotation.*;

import inetex.driver_instructor.dao.*;

public class ScheduleHiber {
	@PersistenceContext(unitName="springHibernate")
	EntityManager em;
	
	@Transactional
	public boolean addItemSchedule(int idInst, int idClient, Date date, String interv, String place) {
		Instructor inst = em.find(Instructor.class, idInst);
		if(inst!=null && getItemSchedule(idInst, date)==null){
			em.persist(new Schedule(inst, idClient, date, interv, place));
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean removeItemSchedule(int idInst, Date date){
		Schedule sched = getItemSchedule(idInst,date);
		if(sched==null) return false;
		em.remove(sched);
		return true;
	}

	@Transactional
	public boolean changeItemSchedule(int idInst, int idClient, Date date, Date newDate,
									String interv, String place, String remark, boolean tenderState){
		Schedule sched = getItemSchedule(idInst,date);
		if(sched==null) return false;
		sched.setDate(newDate);
		sched.setInterval(interv);
		sched.setPlace(place);
		sched.setRemark(remark);
		sched.setTenderState(tenderState);
		em.persist(sched);
		return true;
	}
	
	public Schedule getItemSchedule(int idInst, Date date){
		Query query=em.createQuery("select s from Schedule s where s.instruct.id=?1 and s.date=?2");
		query.setParameter(1, idInst);
		query.setParameter(2, date);
		List<Schedule> res = query.getResultList();
		if(res.isEmpty()) return null;
		return res.get(0);
	}
	
	public List<Schedule> getScheduleByInstructor(int idInst){
		Query query=em.createQuery("select s from Schedule s where s.instruct.id=?1");
		query.setParameter(1, idInst);
		List<Schedule> res = query.getResultList();
		if(res.isEmpty()) return null;
		return res;
	}
	
	public List<Schedule> getScheduleByClient(int idClient){
		Query query=em.createQuery("select s from Schedule s where s.idClient=?1");
		query.setParameter(1, idClient);
		List<Schedule> res = query.getResultList();
		if(res.isEmpty()) return null;
		return res;
	}
	
	
}








