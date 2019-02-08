package com.reavature.tjordan.models;

public class Employee {
		private int emp_ID;
		private String emp_firstName;
		private String emp_lastName;
		private String emp_userName;
		private String emp_password;
		private String emp_email;
		private String isManager;
		public Employee() {
			super();
		}
		public Employee(int emp_ID, String emp_firstName, String emp_lastName, String emp_userName, String emp_password,
				String emp_email, String isManager) {
			super();
			this.emp_ID = emp_ID;
			this.emp_firstName = emp_firstName;
			this.emp_lastName = emp_lastName;
			this.emp_userName = emp_userName;
			this.emp_password = emp_password;
			this.emp_email = emp_email;
			this.isManager = isManager;
		}
		
		
		
		
		public Employee(int emp_ID, String emp_firstName, String emp_lastName, String emp_userName, String emp_email,
				String isManager) {
			super();
			this.emp_ID = emp_ID;
			this.emp_firstName = emp_firstName;
			this.emp_lastName = emp_lastName;
			this.emp_userName = emp_userName;
			this.emp_email = emp_email;
			this.isManager = isManager;
		}
		
		
		public Employee(int emp_ID, String emp_firstName, String emp_lastName, String emp_userName, String emp_email) {
			super();
			this.emp_ID = emp_ID;
			this.emp_firstName = emp_firstName;
			this.emp_lastName = emp_lastName;
			this.emp_userName = emp_userName;
			this.emp_email = emp_email;
		}
		public int getEmp_ID() {
			return emp_ID;
		}
		public void setEmp_ID(int emp_ID) {
			this.emp_ID = emp_ID;
		}
		public String getEmp_firstName() {
			return emp_firstName;
		}
		public void setEmp_firstName(String emp_firstName) {
			this.emp_firstName = emp_firstName;
		}
		public String getEmp_lastName() {
			return emp_lastName;
		}
		public void setEmp_lastName(String emp_lastName) {
			this.emp_lastName = emp_lastName;
		}
		public String getEmp_userName() {
			return emp_userName;
		}
		public void setEmp_userName(String emp_userName) {
			this.emp_userName = emp_userName;
		}
		public String getEmp_password() {
			return emp_password;
		}
		public void setEmp_password(String emp_password) {
			this.emp_password = emp_password;
		}
		public String getEmp_email() {
			return emp_email;
		}
		public void setEmp_email(String emp_email) {
			this.emp_email = emp_email;
		}
		public String getIsManager() {
			return isManager;
		}
		public void setIsManager(String isManager) {
			this.isManager = isManager;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + emp_ID;
			result = prime * result + ((emp_email == null) ? 0 : emp_email.hashCode());
			result = prime * result + ((emp_firstName == null) ? 0 : emp_firstName.hashCode());
			result = prime * result + ((emp_lastName == null) ? 0 : emp_lastName.hashCode());
			result = prime * result + ((emp_password == null) ? 0 : emp_password.hashCode());
			result = prime * result + ((emp_userName == null) ? 0 : emp_userName.hashCode());
			result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
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
			Employee other = (Employee) obj;
			if (emp_ID != other.emp_ID)
				return false;
			if (emp_email == null) {
				if (other.emp_email != null)
					return false;
			} else if (!emp_email.equals(other.emp_email))
				return false;
			if (emp_firstName == null) {
				if (other.emp_firstName != null)
					return false;
			} else if (!emp_firstName.equals(other.emp_firstName))
				return false;
			if (emp_lastName == null) {
				if (other.emp_lastName != null)
					return false;
			} else if (!emp_lastName.equals(other.emp_lastName))
				return false;
			if (emp_password == null) {
				if (other.emp_password != null)
					return false;
			} else if (!emp_password.equals(other.emp_password))
				return false;
			if (emp_userName == null) {
				if (other.emp_userName != null)
					return false;
			} else if (!emp_userName.equals(other.emp_userName))
				return false;
			if (isManager == null) {
				if (other.isManager != null)
					return false;
			} else if (!isManager.equals(other.isManager))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Employee [emp_ID=" + emp_ID + ", emp_firstName=" + emp_firstName + ", emp_lastName=" + emp_lastName
					+ ", emp_userName=" + emp_userName + ", emp_password=" + emp_password + ", emp_email=" + emp_email
					+ ", isManager=" + isManager + "]";
		}
		
		
		
}
