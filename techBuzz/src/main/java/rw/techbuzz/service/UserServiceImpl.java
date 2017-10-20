package rw.techbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.techbuzz.dao.UserDao;
import rw.techbuzz.domain.User;
import rw.techbuzz.exception.DataManipulationException;

@Service
public class UserServiceImpl extends TransactionAware implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public String createUser(User user) {
		try {
			userDao.save(user);
			return "Account created successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("Error:" + e.getMessage());
		}
	}

	@Override
	public String forgotPassord(User user) {
		return null;
	}

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User usernameByEmail(String email) {
		try {
			return userDao.findByUsername(email);
		} catch (Exception e) {
			throw new DataManipulationException("User not found!!");
		}
	}

	@Override
	public String editProfile(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateImage(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

	@Override
	public String activateUser(User u) {
		try {
			User user = userDao.findByUsername(u.getEmail());
			if (user.isState()) {
				user.setState(false);
				userDao.update(user);
				return "User blocked";
			} else {
				user.setState(true);
				userDao.update(user);
				return "User unblocked";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataManipulationException("ServiceError" + e.getMessage());
		}
	}

}
