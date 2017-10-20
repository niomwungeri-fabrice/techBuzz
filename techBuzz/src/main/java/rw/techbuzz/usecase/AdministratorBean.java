package rw.techbuzz.usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.techbuzz.domain.Role;
import rw.techbuzz.domain.User;
import rw.techbuzz.service.AdministratorService;

@ManagedBean
@Component
public class AdministratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Role role = new Role();
	private User user = new User();
	private DualListModel<Role> roles;
	List<Role> allRoles = new ArrayList<Role>();

	@Autowired
	private AdministratorService administratorService;

	
	public void getUserObject(User user) {
		this.user = user;
	}

	public void createRole() {
		String msg = "";
		try {
			msg = administratorService.createRole(role);
			role = new Role();
			successMessages("Succuss", msg);
		} catch (Exception e) {
			errorMessages("Error", msg);
		}
	}

	public void assignRoles() {
		try {
			String msg = "";
			for (Role role : roles.getTarget()) {
				msg = administratorService.assignRoles(role, user.getEmail());
			}
			successMessages("Success", msg);
		} catch (Exception e) {
			errorMessages("Error:", e.getMessage());
		}
	}

	public void onRowEdit(RowEditEvent event) {
		String msg = "";
		try {
			msg = administratorService.updateRole((Role) event.getObject());
			successMessages("Succuss", msg);
		} catch (Exception e) {
			errorMessages("Error", msg);
		}
	}

	@PostConstruct
	public void init() {

		allRoles = administratorService.allRoles();
		
		List<Role> availableRoles = new ArrayList<Role>();
		List<Role> assignedRoles = new ArrayList<Role>();

		for (Role role : administratorService.allRoles()) {
			availableRoles.add(role);
		}
		roles = new DualListModel<Role>(availableRoles, assignedRoles);
	}

	public void onRowCancel(RowEditEvent event) {

	}

	public List<Role> findAllRoles() {
		return administratorService.allRoles();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DualListModel<Role> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void successMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void warningMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void errorMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
