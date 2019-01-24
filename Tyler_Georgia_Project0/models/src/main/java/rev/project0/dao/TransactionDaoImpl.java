package rev.project0.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rev.project0.models.Transaction;
import rev.project0.models.User;
import rev.project0.util.JDBCConnectionUtil;

public class TransactionDaoImpl implements TransactionDao{

	private static TransactionDaoImpl transactionDao;
	
	private TransactionDaoImpl() {
		
	}
	
	public static TransactionDaoImpl getTransactionDaoImpl() {
		if(transactionDao == null) {
			transactionDao = new TransactionDaoImpl();
		}
		return transactionDao;
	}
	
	@Override
	public List<Transaction> getAllTransactionsByAccountId(int accountId) {
		ResultSet queryResult = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			
			String sql = "Select * from bank_transaction where account_id = \'" + accountId + "\' order by transaction_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			queryResult = ps.executeQuery();
	 
			while(queryResult.next()) {
				
				int transactionId = queryResult.getInt("transaction_id");
				String transactionType = queryResult.getString("transaction_type");
				int value = queryResult.getInt("amount");
				int accountIdQryVal = queryResult.getInt("account_id");
				
				Transaction currTransaction = new Transaction(transactionId, transactionType, value, accountIdQryVal);
				transactions.add(currTransaction);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error obtaining Transactions in getAllTransactionsByAccountId");
		}
		
		return transactions;		
	}

	@Override
	public boolean insertTransaction( String type, int value, int accountId) {

		boolean transactionInserted = true;
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			
			String sql = "call insert_transaction(?, ?, ?)" ;
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, type);
			ps.setInt(2, value);
			ps.setInt(3, accountId);
			if(ps.executeUpdate() == 1)
				System.out.println("EXECUTE_UPDATE RETURNS EXPECTED VALUE FOR BOOLEAN ASSIGNMENT");;
	 	
		} catch (SQLException e) {
			e.printStackTrace();
			transactionInserted = false;
		}
		
		return transactionInserted;
	}

}
