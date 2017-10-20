package rw.techbuzz.service;

import java.util.List;

import rw.techbuzz.domain.Role;
import rw.techbuzz.domain.UserRole;

public interface AdministratorService {

	String createRole(Role role);

	List<Role> allRoles();

	String updateRole(Role role);

	String assignRoles(Role role, String userId);

	List<UserRole> rolesByUser(Long userId);
}
