package inetex.driver_instructor.implementation;

import java.util.*;

import javax.persistence.*;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import inetex.driver_instructor.dao.*;

public class InstructorHiber {
@PersistenceContext(unitName="springHibernate")
EntityManager em;

@Transactional
public boolean addInstructor(String name, String email, Set<String> phone, String sex, 
		Address address, String typeVehicle, String transmission){
	if(getInstructor(email)!=null) return false;
	em.persist(new Instructor(name, email, phone, sex, address, typeVehicle, transmission));
	return true;
}

@Transactional
public boolean removeInstructor(String email){
	Instructor inst = getInstructor(email);
	if(inst==null) return false;
	em.remove(inst);
	return true;
}

public Instructor getInstructor(String email){
	Query query=em.createQuery("select i from Instructor i where i.email=?1");
	query.setParameter(1, email);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res.get(0);
}

public Instructor getInstructorById(int id){
	Instructor inst = em.find(Instructor.class, id);
	if(inst==null) return null;
	return inst;
}

public List<Instructor> getInstructorByTypeVehicle(String typeVehicle){
	Query query=em.createQuery("select i from Instructor i where i.typeVehicle=?1");
	query.setParameter(1, typeVehicle);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res;
}

public List<Instructor> getInstructorByTransmission(String transmission){
	Query query=em.createQuery("select i from Instructor i where i.transmission=?1");
	query.setParameter(1, transmission);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res;
}

public List<Instructor> getInstructorBySex(String sex){
	Query query=em.createQuery("select i from Instructor i where i.sex=?1");
	query.setParameter(1, sex);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res;
}

public List<Instructor> getInstructorByArea(String area){
	Query query=em.createQuery("select i from Instructor i where i.address.area=?1");
	query.setParameter(1, area);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res;
}

public List<Instructor> getInstructorByCity(String area, String city){
	Query query=em.createQuery("select i from Instructor i where i.address.area=?1 "
			+"and i.address.city=?2");
	query.setParameter(1, area);
	query.setParameter(2, city);
	List<Instructor> res = query.getResultList();
	if(res.isEmpty()) return null;
	return res;
}

@Transactional
public boolean changeEmail(int id, String newEmail){
	Instructor inst = getInstructorById(id);
	if(inst==null || getInstructor(newEmail)!=null) return false;
	inst.setEmail(newEmail);
	em.persist(inst);
	return true;
}

@Transactional
public Set<String> getPhones(int id){
	Instructor inst = getInstructorById(id);
	if(inst==null) return null;
	Hibernate.initialize(inst.getPhone());
    return inst.getPhone();
}

@Transactional
public boolean addPhone(int id, String phone){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	Set<String> phones = inst.getPhone();
	phones.add(phone);
	inst.setPhone(phones);
	em.persist(inst);
	return true;
}

@Transactional
public boolean removePhone(int id, String phone){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	Set<String> phones = inst.getPhone();
	if(!phones.remove(phone)) return false;	
	inst.setPhone(phones);
	em.persist(inst);
	return true;
}

@Transactional
public List<String> getWorkingDays(int id){
	Instructor inst = getInstructorById(id);
	if(inst==null) return null;
	Hibernate.initialize(inst.getWorkingDays());
    return inst.getWorkingDays();
}

@Transactional
public boolean setWorkingDays(int id, List<String> workingDays){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	inst.setWorkingDays(workingDays);
	em.persist(inst);
	return true;
}

@Transactional
public List<String> getWorkingHours(int id){
	Instructor inst = getInstructorById(id);
	if(inst==null) return null;
	Hibernate.initialize(inst.getWorkingHours());
    return inst.getWorkingHours();
}

@Transactional
public boolean setWorkingHours(int id, List<String> workingHours){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	inst.setWorkingHours(workingHours);
	em.persist(inst);
	return true;
}

@Transactional
public boolean changeAddress(int id, Address address){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	inst.setAddress(address);
	em.persist(inst);
	return true;
}

@Transactional
public boolean changeVehicle(int id, String typeVehicle, String transmission){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	inst.setTypeVehicle(typeVehicle);
	inst.setTransmission(transmission);
	em.persist(inst);
	return true;
}

@Transactional
public boolean changeURL(int id, String url){
	Instructor inst = getInstructorById(id);
	if(inst==null) return false;
	inst.setUrl(url);
	em.persist(inst);
	return true;
}

}
















