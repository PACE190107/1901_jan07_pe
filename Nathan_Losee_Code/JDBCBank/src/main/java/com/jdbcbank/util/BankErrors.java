package com.jdbcbank.util;

import java.sql.SQLException;

public class BankErrors {
	public static class NonEmptyAccountException extends SQLException {
		private static final long serialVersionUID = -326697623007882695L;
	}
	public static class InvalidTypeException extends SQLException {
		private static final long serialVersionUID = -9074897993088996819L;
	}
	public static class InvalidAmountException extends SQLException {
		private static final long serialVersionUID = 6933588360860874091L;
	}
	public static class OverdraftException extends SQLException {
		private static final long serialVersionUID = -8058879747844588872L;
	}
	public static class InvalidAccountIDException extends SQLException {
		private static final long serialVersionUID = 3067997247974500191L;
	}
	public static class InvalidUsernamePasswordException extends SQLException {
		private static final long serialVersionUID = 5834183009812577348L;
	}
	public static class ExistingUsernamePasswordException extends SQLException {
		private static final long serialVersionUID = 519042640220138945L;
	}
	public static class MalformedUsernamePasswordException extends SQLException {
		private static final long serialVersionUID = 890900388646858846L;
	}
	public static class MalformedAmountException extends SQLException {
		private static final long serialVersionUID = 7023530591971651957L;
	}
}
