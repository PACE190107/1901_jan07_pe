package rev.project0.dao;
import java.util.List;

import rev.project0.models.*;
public interface TransactionDao {
	
	public List<Transaction> getAllTransactionsByAccountId(int pAccountId);
	public boolean insertTransaction( String type, int value, int accountId);
	
	
}
