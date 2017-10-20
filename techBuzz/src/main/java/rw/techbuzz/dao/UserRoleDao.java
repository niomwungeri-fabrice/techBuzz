package rw.techbuzz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import rw.techbuzz.domain.UserRole;
import rw.techbuzz.genericdao.GenericDaoImpl;
import rw.techbuzz.util.QueryParameters;

@Repository
public class UserRoleDao extends GenericDaoImpl<UserRole> {
	public static final String NAME = "UserRoleDao";

	public static class QUERY {
		public static final String roleByUser = "select ur from UserRole ur where ur.user.id =:id";

	}

	public static class QUERY_NAME {
		public static final String roleByUser = "UserRole.roleByUser";

	}

	public List<UserRole> roleByUser(final Long id) {
		QueryParameters props = new QueryParameters().add("id", id);
		return this.executeNamedQueryMultiple(QUERY_NAME.roleByUser, props);
	}

}
