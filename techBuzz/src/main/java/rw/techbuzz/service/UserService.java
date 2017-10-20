package rw.techbuzz.service;

import java.util.List;

import rw.techbuzz.domain.User;

public interface UserService {

	String createUser(User user);

	String forgotPassord(User user);

	String login(User user);

	User usernameByEmail(String email);

	String editProfile(User user);

	User updateImage(User user);

	List<User> findAllUser();

	String activateUser(User user);
}
