package rev.project0.models;
enum TransactionType {WITHDRAWAL, DEPOSIT}
public class Transaction {
	private String date; //possibly with a date object
	private int value;
	private String transactionType; //based on change
	private int transactionId;
	private int accountId;
	
	public Transaction(String type, int value, int accountId) {
		this.transactionType = type;
		this.value = value;
		this.accountId = accountId;
	}
	
	public Transaction(int transactionId, String type, int value, int accountId) {
		this.transactionId = transactionId;
		this.transactionType = type;
		this.value = value;
		this.accountId = accountId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
	
}
