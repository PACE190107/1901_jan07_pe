package rev.project0.dao;

import java.util.List;

import rev.project0.models.User;

public interface UserDao {

	public boolean createUser(User newUser);
	//
	public User getUser(String userName, String password);
	
	public List<User> getAllUsers();
	
	public boolean checkForUserByUsername(String username);
	
	public boolean deleteUser(int userId);
	
	public boolean updateUsername(String username, int userId);
	
	public boolean updatePassword(String password, int userId);
	
}
