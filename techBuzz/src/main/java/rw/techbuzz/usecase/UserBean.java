package rw.techbuzz.usecase;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.techbuzz.domain.User;
import rw.techbuzz.service.UserService;

@Component
@ManagedBean
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	private User user = new User();

	private String username;
	private String password;

	public String login() {
		try {
			User user = userService.usernameByEmail(username);
			if (user == null) {
				errorMessages("Error:", "User not found");
				return "login.xhtml";
			} else {
				return "post.xhtml";
			}
		} catch (Exception e) {
			errorMessages("Error:", e.getMessage());
			return "login.xhtml";
		}
	}

	public void createAccount() {
		String msg = "";
		try {
			msg = userService.createUser(user);
			user = new User();
			successMessages("Success", msg);
		} catch (Exception e) {
			errorMessages("Error", msg);
		}
	}

	public void activateUser(User user) {
		try {
			String msg = userService.activateUser(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error>>" + e.getMessage(), null));
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> findAllUser() {
		return userService.findAllUser();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
