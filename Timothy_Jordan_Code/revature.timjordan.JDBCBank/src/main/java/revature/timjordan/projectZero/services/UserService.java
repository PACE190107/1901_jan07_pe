package revature.timjordan.projectZero.services;

import java.util.List;

import revature.timjordan.projectZero.dao.AccountDaoImplementation;
import revature.timjordan.projectZero.dao.UserDaoImplementation;
import revature.timjordan.projectZero.models.Account;
import revature.timjordan.projectZero.models.User;

public class UserService {
	
	private static UserService currentUserService;
	
		private UserService() {
			
		}
		
		public static UserService getUserService() {
			if(currentUserService == null) {
				currentUserService = new UserService();
			}
			
			return currentUserService;
		}
		
		public List<User> getAllUserDetails() {
			return UserDaoImplementation.getUserDao().getAllUsers();
		}
		
		public boolean registerUser(User currentUser) {
			return UserDaoImplementation.getUserDao().insertUser(currentUser);
			
		}
		
		public boolean registerUserProcedure(User currentUser) {
			return UserDaoImplementation.getUserDao().insertUserProcedure(currentUser);
		}
		
		public User getUser(String userName) {
			return UserDaoImplementation.getUserDao().getUser(userName);
		}
		
		public boolean isUser(User currentUser) {
			return UserDaoImplementation.getUserDao().isUser(currentUser);
		}
		
		public int getUserId(User currentUser) {
			return UserDaoImplementation.getUserDao().getUserId(currentUser);
		}
		
		public boolean isPassword(User currentUser) {
			return UserDaoImplementation.getUserDao().isPassword(currentUser);
		}
		
		public boolean addUser(User currentUser) {
			return UserDaoImplementation.getUserDao().addUser(currentUser);
		}
		
		public boolean addChecking(User currentUser) {
			return AccountDaoImplementation.getAccountDao().addChecking(currentUser);
		}
		
		public boolean deleteUser(User currentUser) {
			return UserDaoImplementation.getUserDao().deleteUser(currentUser);
		}
		
		public boolean modifyUser(String columnName, String newValue, int user_ID) {
			return UserDaoImplementation.getUserDao().modifyUser(columnName, newValue, user_ID);
		}
		
		public boolean removeSavings(Account currentAccount) {
			return AccountDaoImplementation.getAccountDao().removeSavings(currentAccount);
		}
		
		public boolean addSavings(User currentUser) {
			return AccountDaoImplementation.getAccountDao().addSavings(currentUser);
		}
		
		public List<Account> getAccounts(User currentUser, String type, int number) {
			return AccountDaoImplementation.getAccountDao().getAccounts(currentUser, type, number);
		}
		
		public boolean withdraw(Account tempAccount, double amount) {
			return AccountDaoImplementation.getAccountDao().withdraw(tempAccount, amount);
		}
		
		public boolean deposit(Account tempAccount, double amount) {
			return AccountDaoImplementation.getAccountDao().deposit(tempAccount, amount);
		}
		
		public boolean hasSavings(User currentUser) {
			return AccountDaoImplementation.getAccountDao().hasSavings(currentUser);
		}
	
}
