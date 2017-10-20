package rw.techbuzz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rw.techbuzz.dao.UserRoleDao;

@Entity
@Table(name = "USER_ROLE")
@NamedQueries({ @NamedQuery(name = UserRoleDao.QUERY_NAME.roleByUser, query = UserRoleDao.QUERY.roleByUser) })
public class UserRole extends GenericDomain {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = Constants.ID_GENERATOR)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_UUID")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLE_UUID")
	private Role role;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
