package com.revautre.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.JDBCConnectionUtil;

public class ReimbursementDaoImplementation implements ReimbursementDao {

	
		
		
		private static ReimbursementDaoImplementation reimbDao;
			
			final static Logger log = Logger.getLogger(ReimbursementDaoImplementation.class);
			
			private ReimbursementDaoImplementation() {
			}
			
			public static ReimbursementDaoImplementation getCustDao() {
				if (reimbDao == null) {
					reimbDao = new ReimbursementDaoImplementation();
				}
				return reimbDao;
			}
			
			
/////////////////////////////////////////////////////////////////////			
			public Reimbursement newRequest(Reimbursement request) {
				Connection conn = null;
				CallableStatement stmt = null;
				//Date date_received, String type, int amount, String description
				
		try (conn = JDBCConnectionUtil.getConnection() ){
					stmt = conn.prepareCall("{CALL NEW_REIMBURSEMENT(?,?,?,?)}");
					
				
					stmt.setDate(1, request.getDate_received());
					stmt.setString(2, request.getType());
					stmt.setInt(3, request.getAmount());
					stmt.setString(4, request.getDescription());
			
					
					
					
					ResultSet resultSet = stmt.executeQuery();
					System.out.println(resultSet);
					//System.out.println(resultSet.next());
					

					if(stmt.executeUpdate() >= 0) 
					{
						return request;
					} 
					
				else {
						throw new SQLException();
					}
					
				} catch (SQLException e) {
					
					log.error("error occured in reimbursement implementation method");
					log.error(e.getMessage());
					log.error(e.getStackTrace());
					return null;	
				
					
					
					
								
								
								
								
					
					}
						
						
					
					
				
			}
			
/////////////////////////////////////////////////////////////////////////////////////			
			
			
			
			
}
