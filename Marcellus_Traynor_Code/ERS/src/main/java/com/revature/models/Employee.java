package com.revature.models;

public class Employee 
{
	private String fName;
	private String lName;
	private String job;
	private String uName;
	private String pWord;
	
	public Employee() 
	{
		super();
	}
	
	public Employee(String fName, String lName, String job, String pWord) 
	{
		super();
		this.fName = fName;
		this.lName = lName;
		this.job = job;
		this.pWord = pWord;
	}
	
	public Employee(String fName, String lName, String job, String uName, String pWord) 
	{
		super();
		this.fName = fName;
		this.lName = lName;
		this.job = job;
		this.uName = uName;
		this.pWord = pWord;
	}
	
	public String getfName() 
	{
		return fName;
	}
	
	public void setfName(String fName) 
	{
		this.fName = fName;
	}
	
	public String getlName() 
	{
		return lName;
	}
	
	public void setlName(String lName) 
	{
		this.lName = lName;
	}
	
	public String getJob() 
	{
		return job;
	}
	
	public void setJob(String job) 
	{
		this.job = job;
	}
	
	public String getuName() 
	{
		return uName;
	}
	
	public void setuName(String uName) 
	{
		this.uName = uName;
	}
	
	public String getpWord() 
	{
		return pWord;
	}
	
	public void setpWord(String pWord) 
	{
		this.pWord = pWord;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((pWord == null) ? 0 : pWord.hashCode());
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Employee other = (Employee) obj;
		if (fName == null) 
		{
			if (other.fName != null)
				return false;
		} 
		else if (!fName.equals(other.fName))
			return false;
		
		if (job == null) 
		{
			if (other.job != null)
				return false;
		}
		else if (!job.equals(other.job))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		
		if (pWord == null)
		{
			if (other.pWord != null)
				return false;
		} 
		else if (!pWord.equals(other.pWord))
			return false;
		
		if (uName == null) 
		{
			if (other.uName != null)
				return false;
		} 
		else if (!uName.equals(other.uName))
			return false;
		
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "Employee [fName=" + fName + ", lName=" + lName + ", job=" + job + ", uName=" + uName + ", pWord="
				+ pWord + "]";
	}
}