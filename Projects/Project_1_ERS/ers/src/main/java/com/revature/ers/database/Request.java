package com.revature.ers.database;

public class Request
	{
		private int reqID;
		private int empID;
		private double amount;
		private String category;
		private String reqComments;
		
		private String manComments;
		private int manID;
		private int approved;
		
		public Request()
		{
			
		}
		
//		public Request(int reqID, int empID, double amount, String category, String reqComments)
//			{
//				super();
//				this.reqID = reqID;
//				this.empID = empID;
//				this.amount = amount;
//				this.category = category;
//				this.reqComments = reqComments;
//				this.approved = 0;
//				this.manComments = null;
//				this.manID = 0;
//			}

		public Request(int reqID, int empID, double amount, String category, String reqComments, int approved,
				String manComments, int manID)
			{
				super();
				this.reqID = reqID;
				this.empID = empID;
				this.amount = amount;
				this.category = category;
				this.reqComments = reqComments;
				this.approved = approved;
				this.manComments = manComments;
				this.manID = manID;
			}
		public int getReqID()
			{
				return reqID;
			}
		public void setReqID(int reqID)
			{
				this.reqID = reqID;
			}
		public int getEmpID()
			{
				return empID;
			}
		public void setEmpID(int empID)
			{
				this.empID = empID;
			}
		public double getAmount()
			{
				return amount;
			}
		public void setAmount(double amount)
			{
				this.amount = amount;
			}
		public String getCategory()
			{
				return category;
			}
		public void setCategory(String category)
			{
				this.category = category;
			}
		public String getReqComments()
			{
				return reqComments;
			}
		public void setReqComments(String reqComments)
			{
				this.reqComments = reqComments;
			}
		public int isApproved()
			{
				return approved;
			}
		public void setApproved(int approved)
			{
				this.approved = approved;
			}
		public String getManComments()
			{
				return manComments;
			}
		public void setManComments(String manComments)
			{
				this.manComments = manComments;
			}
		public int getManID()
			{
				return manID;
			}
		public void setManID(int manID)
			{
				this.manID = manID;
			}

		@Override
		public String toString()
			{
				return "Request [reqID=" + reqID + ", empID=" + empID + ", amount=" + amount + ", category=" + category
						+ ", reqComments=" + reqComments + ", approved=" + approved + ", manComments=" + manComments
						+ ", manID=" + manID + "]";
			}
		
		
		
	}
