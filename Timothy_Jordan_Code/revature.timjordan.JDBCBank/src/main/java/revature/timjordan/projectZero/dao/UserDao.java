package revature.timjordan.projectZero.dao;

import java.util.List;

import revature.timjordan.projectZero.models.User;

public interface UserDao {
		public boolean insertUser(User currentUser);
		public boolean insertUserProcedure(User currentUser);
		public User getUser(String userName);
		public int getUserId(User currentUser);
		public boolean addUser(User currentUser);
		public boolean isUser(User currentUser);
		public boolean isPassword(User currentUser);
		public List<User> getAllUsers();
		public boolean deleteUser(User currentUser);
		public boolean modifyUser(String columnName, String newValue, int user_ID);
}
