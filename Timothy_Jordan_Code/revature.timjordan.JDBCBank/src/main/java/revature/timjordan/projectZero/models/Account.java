package revature.timjordan.projectZero.models;

public class Account {
		private int accountID;
		private int userID;
		private String accountType;
		private double accountBalance;
		
		
		public Account() {
			super();
		}


		public Account(int accountID, int userID, String accountType, double accountBalance) {
			super();
			this.accountID = accountID;
			this.userID = userID;
			this.accountType = accountType;
			this.accountBalance = accountBalance;
		}
		
		public Account(int userID, String accountType, double accountBalance) {
			super();
			this.userID = userID;
			this.accountType = accountType;
			this.accountBalance = accountBalance;
		}
		
		


		public Account(int accountID, String accountType) {
			super();
			this.accountID = accountID;
			this.accountType = accountType;
		}


		public int getAccountID() {
			return accountID;
		}


		public void setAccountID(int accountID) {
			this.accountID = accountID;
		}


		public int getUserID() {
			return userID;
		}


		public void setUserID(int userID) {
			this.userID = userID;
		}


		public String getAccountType() {
			return accountType;
		}


		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}


		public double getAccountBalance() {
			return accountBalance;
		}


		public void setAccountBalance(double accountBalance) {
			this.accountBalance = accountBalance;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(accountBalance);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + accountID;
			result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
			result = prime * result + userID;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Account other = (Account) obj;
			if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
				return false;
			if (accountID != other.accountID)
				return false;
			if (accountType == null) {
				if (other.accountType != null)
					return false;
			} else if (!accountType.equals(other.accountType))
				return false;
			if (userID != other.userID)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Account [accountID=" + accountID + ", userID=" + userID + ", accountType=" + accountType
					+ ", accountBalance=" + accountBalance + "]";
		}


		
		
		
}
