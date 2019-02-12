package com.revatue.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.services.RequestService;
import com.revature.utils.JDBCConnectionUtil;

public class ReimbursementDaoImplementation implements ReimbursementDao {

	
		
		
		private static ReimbursementDaoImplementation reimbDao;
			
			final static Logger log = Logger.getLogger(ReimbursementDaoImplementation.class);
			
			private ReimbursementDaoImplementation() {
			}
			
			public static ReimbursementDaoImplementation getreimbDao() {
				if (reimbDao == null) {
					reimbDao = new ReimbursementDaoImplementation();
				}
				return reimbDao;
			}
			
			
			
			
//works!!
//Method to make a new reimbursement///////////////////////////////////////////////////////////////////			
			public Reimbursement newRequest( int e_id, String date_received, String type, double amount, String description) {
				
				System.out.println("inside dao implementation");
				Reimbursement request = new Reimbursement();
				
				
		try (Connection conn = JDBCConnectionUtil.getConnection() ){
			String sql= ("INSERT INTO REIMBURSEMENTS (REQ_ID, E_ID, DATE_RECEIVED, REQ_TYPE, AMOUNT, REQ_DESCRIPTION) VALUES (REQ_ID_SEQ.NEXTVAL,?,?,?,?,?)");
			PreparedStatement ps = conn.prepareStatement(sql);		
			
			System.out.println("INSIDE TRY BLOCK");
			//System.out.println(seq_id);
			System.out.println(e_id);
			System.out.println(date_received);
			System.out.println(type);
			System.out.println(amount);
			System.out.println(description);
		

			
					
					//ps.setInt(1, seq_id);
					ps.setInt(1, e_id);
					ps.setString(2, date_received);
					ps.setString(3, type);
					ps.setDouble(4, amount);
					ps.setString(5, description);
					
					
					
					
					//System.out.println(seq_id);
					System.out.println(e_id);
					System.out.println(date_received);
					System.out.println(type);
					System.out.println(amount);
					System.out.println(description);
				
			
					
					
					
				

					if(ps.executeUpdate() == 1) 
					{System.out.println("SQLTrue");
						return request;
						
					} 
					
				else { System.out.println("SQLFalse");
						throw new SQLException();
					}
					
				} catch (SQLException e) {
					
					System.out.println("error occured in reimbursement implementation method");
					log.error(e.getMessage());
					log.error(e.getStackTrace());
					return null;	
				
					
					
					
								
								
								
								
					
					}
						
						
					
					
				
			}

			
//WORKS
//Employee Method to view all employees' reimbursements ///////////////////////////////////////////////////////////////////////////////////			
			
	public List<Reimbursement> viewEmployeeRequests(int e_id) {
				
				System.out.println("insideReimbursementImplementation");
				
				
		try ( Connection conn = JDBCConnectionUtil.getConnection() ){
				
					
					String sql= "SELECT * FROM REIMBURSEMENTS where E_ID = " +e_id  + "";
					PreparedStatement ps = conn.prepareStatement(sql);
				
				
					
					ResultSet resultSet = ps.executeQuery();
					System.out.println(resultSet);
				
					
					
					System.out.println("in while");
					
					List <Reimbursement> temprequest= new ArrayList<>();
					while(resultSet.next()) {
					
					
						temprequest.add(new Reimbursement(
								resultSet.getInt("REQ_ID"),
								resultSet.getInt("E_ID"),
								resultSet.getString("DATE_RECEIVED"),
								resultSet.getString("REQ_TYPE"),
								resultSet.getInt("AMOUNT"),
								resultSet.getString("REQ_DESCRIPTION"),
								resultSet.getString("STATUS"),
								resultSet.getInt("MANAGER_ID")
								
								
								
								
								));
					
						
					
					}
						
						
					
					return temprequest;
					
					
				
					
				
				} catch (SQLException e) {
					e.printStackTrace();
				System.out.println("inSQL Exception");
				}
		return new ArrayList<Reimbursement>();
		 
			}
			
	//works!!		
//Manager Method to view all pending requests///////////////////////////////////////////////////////////////////////////	
	
			public List<Reimbursement> viewPendingRequests(){
									
			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			
				String sql= "SELECT * FROM REIMBURSEMENTS where STATUS = 'pending'";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				
				ResultSet resultSet = ps.executeQuery();
				System.out.println(resultSet);
				//System.out.println(resultSet.next());
				
				List <Reimbursement> request= new ArrayList<>();
				while(resultSet.next()) {
					System.out.println("in while");
					
					request.add(new Reimbursement(
							resultSet.getInt("REQ_ID"),
							resultSet.getInt("E_ID"),
							resultSet.getString("DATE_RECEIVED"),
							resultSet.getString("REQ_TYPE"),
							resultSet.getInt("AMOUNT"),
							resultSet.getString("REQ_DESCRIPTION"),
							resultSet.getString("STATUS"),
							resultSet.getInt("MANAGER_ID")
							
							
							
							
							));
				return request;
				}
				
		
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return new ArrayList<Reimbursement>();
			}
					
			
		
//Manager Method to view all resolved requests////////////////////////////////////////////////////////////////////////////			
			public List<Reimbursement> viewResolvedRequests(){
				
			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			
				String sql= "SELECT * FROM REIMBURSEMENTS where STATUS != 'PENDING'";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				
				ResultSet resultSet = ps.executeQuery();
				System.out.println(resultSet);
				//System.out.println(resultSet.next());
				
				List <Reimbursement> request= new ArrayList<>();
				//List <Reimbursement> request= new ArrayList<>();
				while(resultSet.next()) {
					System.out.println("in while");
					
					request.add(new Reimbursement(
							resultSet.getInt("REQ_ID"),
							resultSet.getInt("E_ID"),
							resultSet.getString("DATE_RECEIVED"),
							resultSet.getString("REQ_TYPE"),
							resultSet.getInt("AMOUNT"),
							resultSet.getString("REQ_DESCRIPTION"),
							resultSet.getString("STATUS"),
							resultSet.getInt("MANAGER_ID")
							
							
							
							
							));
					
				
				
				}
				if(request == null) {
					
					return null;
				} else {
					System.out.println("in else");
					return request;
				}
		
		
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			}
					
			
			
			
			
			
			
// approveDeny/////////////////////////////////////////
			
			
			
			public void approveDeny (int seq_id, String status, int manager_id) {
				
				
				
				
				
				
				try ( Connection conn = JDBCConnectionUtil.getConnection() ){
					
					String sql= "UPDATE REIMBURSEMENTS SET MANAGER_ID = " +manager_id+ ", STATUS = '" + status+ "' WHERE REQ_ID =" +seq_id;
							
							//UPDATE REIMBURSEMENTS SET MANAGER_ID = 12345, STATUS = 'APPROVED'  WHERE REQ_ID =500;
					PreparedStatement ps = conn.prepareStatement(sql);
					
					
					ResultSet resultSet = ps.executeQuery();
				
				
					
					
			
				
			}catch (SQLException e) {
				e.printStackTrace();
				return;
			
			}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
		//////////////////	

			@Override
			public boolean updateRequest(String date_resolved, String status, int manager_id) {
				// TODO Auto-generated method stub
				return false;
			}


			@Override
			public List<Reimbursement> viewRequests(int e_id) {
				// TODO Auto-generated method stub
				return null;
			}

			

			
}
