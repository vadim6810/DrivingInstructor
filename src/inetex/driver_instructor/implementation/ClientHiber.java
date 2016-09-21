package inetex.driver_instructor.implementation;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.callback.PasswordCallback;

import org.springframework.transaction.annotation.Transactional;

import inetex.driver_instructor.dao.Address;
import inetex.driver_instructor.dao.Client;

public class ClientHiber {
	@PersistenceContext(unitName="springHibernate")
	EntityManager em;

	@Transactional
	public boolean addClient(String clientName, String email, Set <String> phone,PasswordCallback password){
		if(getClientByEmaile(email) != null) return false;
		em.persist(new Client(clientName, email, phone, password));
		return true;
	}
	
	private Client getClientByEmaile(String email){
		Client client = em.find(Client.class, email);
		if(client == null) return null;
		return client;
	}
	
	private Client getClientByName(String clientName) {
		Client client = em.find(Client.class, clientName);
		if(client == null) return null;
		return client;
	}
	
	@Transactional
	public boolean changeEmail(String email, String newEmail){
		Client client = getClientByEmaile(email);
		if(client.getEmaile() != email) return false;
		client.setEmaile(newEmail);
		em.persist(client);
		return true;
	}
	
	@Transactional
	public boolean addAvatar(Client client,String avatar){
		if(client.getAvatar() != null) return false;
		client.setAvatar(avatar);
		return true;
	}
	
	@Transactional
	public boolean changeAvatar(String avatar, String newAvatar){
		Client client = getClientByName(avatar);
		if(client == null) return false;
		client.setAvatar(newAvatar);
		em.persist(client);
		return true;
	}
	
	@Transactional
	public boolean removeAvatar(String avatar){
		Client client = getClientByName(avatar);
		if(client.getAvatar() == null) return false;
		avatar = null;
		client.setAvatar(avatar);
		em.persist(client);
		return true;
	}
	
	@Transactional
	public boolean delClient(String email){
		Client client = getClientByEmaile(email);
		client = null;
		em.persist(client);
		return true;
	}
	
	@Transactional
	public boolean changeArea(String email,Address newArea){
		Client client = getClientByEmaile(email);
		if(client.getEmaile() != email) return false;
		client.setArea(newArea);
		em.persist(client);
		return true;
	}
	
	@Transactional
	public boolean changePassword (String email,PasswordCallback newPassword){
		Client client = getClientByEmaile(email);
		if(client.getPassword() == null) return false;
		client.setPassword(newPassword);
		em.persist(client);
		return true;
	}
	
	/*@Transactional
	public boolean checkRegClientData(String email, PasswordCallback password){
		Client client = getClientByEmaile(email);
		if(!client.getEmaile().equals(email) && !client.getPassword().equals(password)) return false;
		return true;
	}*/
}
