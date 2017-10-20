package rw.techbuzz.dao;

import org.springframework.stereotype.Repository;

import rw.techbuzz.domain.User;
import rw.techbuzz.genericdao.GenericDaoImpl;
import rw.techbuzz.util.QueryParameters;

@Repository
public class UserDao extends GenericDaoImpl<User> {
	public static final String NAME = "UserDao";

	public static class QUERY {
		public static final String findByUsername = "select e from User e where e.email = :email";

	}

	public static class QUERY_NAME {

		public static final String findByUsername = "User.findByUsername";

	}

	public User findByUsername(final String email) {
		QueryParameters props = new QueryParameters().add("email", email);
		return this.executeNamedQuerySingle(QUERY_NAME.findByUsername, props);
	}
}
