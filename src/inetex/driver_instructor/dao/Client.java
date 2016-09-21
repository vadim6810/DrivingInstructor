package inetex.driver_instructor.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.security.auth.callback.PasswordCallback;

@Entity
public class Client {
@Id
@GeneratedValue
int id;
String clientName;
String email;
@ElementCollection	//(fetch = FetchType.EAGER)
Set<String> phone;
@Embedded
Address area;
String avatar;
PasswordCallback password;

public Client() {
	super();
}


public Client(String clientName, String emaile, Set<String> phone, int id, Address area, List<Schedule> schedItems, String avatar, PasswordCallback password) {
	super();
	this.id = id;
	this.clientName = clientName;
	this.email = emaile;
	this.phone = phone;
	this.area = area;
	this.avatar = avatar;
	this.password = password;
}

public Client(String clientName, String emaile, Set<String> phone, PasswordCallback password) {
	super();
	this.clientName = clientName;
	this.email = emaile;
	this.phone = phone;
	this.password = password;
}

public int getId() {
	return id;
}

public String getClientName() {
	return clientName;
}

public String getEmaile() {
	return email;
}

public Set<String> getPhone() {
	return phone;
}

public Address getAddress() {
	return area;
}

public String getAvatar() {
	return avatar;
}

public void setClientName(String clientName) {
	this.clientName = clientName;
}

public Address getArea() {
	return area;
}


public void setArea(Address area) {
	this.area = area;
}


public void setEmaile(String emaile) {
	this.email = emaile;
}

public void setPhone(Set<String> phone) {
	this.phone = phone;
}

public void setAddress(Address area) {
	this.area = area;
}

public void setAvatar(String avatar) {
	this.avatar = avatar;
}

public PasswordCallback getPassword() {
	return password;
}


public void setPassword(PasswordCallback password) {
	this.password = password;
}
}
