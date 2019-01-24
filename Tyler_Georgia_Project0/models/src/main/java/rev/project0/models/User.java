package rev.project0.models;

import java.util.List;

public class User {
	
	private String userName;
	private int userId;
	private List<Account> accounts;
	private String password;
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public User (String userName,  int userId) {
		this.userName = userName;
		this.userId = userId;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
