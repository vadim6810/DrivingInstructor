package inetex.driver_instructor.dao;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	Instructor instruct;
	private int idClient;
	private Date date;
	private String interv;
	private String remark;
	private String place;
	private boolean tenderState;
	
	protected Schedule() {
		super();
	}
	public Schedule(Instructor inst, int idClient, Date date, String interv, String place) {
		super();
		this.instruct = inst;
		this.idClient = idClient;
		this.date = date;
		this.interv = interv;
		this.place = place;
	}
	
	public int getId() {
		return id;
	}
	public Instructor getInstructor() {
		return instruct;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInterval() {
		return interv;
	}
	public void setInterval(String interval) {
		this.interv = interval;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public boolean isTenderState() {
		return tenderState;
	}
	public void setTenderState(boolean tenderState) {
		this.tenderState = tenderState;
	}
	
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", instructor=" + instruct + ", idClient=" + idClient + ", date=" + date + ", place="
				+ place + "]";
	}
	
}
