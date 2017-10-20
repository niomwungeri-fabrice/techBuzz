package rw.techbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.techbuzz.dao.RoleDao;
import rw.techbuzz.dao.UserDao;
import rw.techbuzz.dao.UserRoleDao;
import rw.techbuzz.domain.Role;
import rw.techbuzz.domain.User;
import rw.techbuzz.domain.UserRole;
import rw.techbuzz.exception.DataManipulationException;

@Service
public class AdministratorServiceImpl extends TransactionAware implements AdministratorService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao UserDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public String createRole(Role role) {
		try {
			roleDao.save(role);
			return "Role created successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ErrorMethod:" + e.getMessage());
		}
	}

	@Override
	public List<Role> allRoles() {
		return roleDao.findAll();
	}

	@Override
	public String assignRoles(Role role, String userId) {
		try {
			User user = UserDao.findByUsername(userId);
			UserRole uRoles = new UserRole();
			uRoles.setUser(user);
			uRoles.setRole(role);
			userRoleDao.save(uRoles);
			return "Role(s) Assigned Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException(e.getMessage());
		}
	}

	@Override
	public String updateRole(Role role) {
		try {
			roleDao.update(role);
			return "Role updated successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ErrorMethod:" + e.getMessage());
		}
	}

	@Override
	public List<UserRole> rolesByUser(Long userId) {
		return userRoleDao.roleByUser(userId);
	}

}
