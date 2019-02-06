package com.ers.util;

public class ERSExceptions {
	private ERSExceptions() { }
	
	public static class NonEmptyAccountException extends RuntimeException {
		private static final long serialVersionUID = -326697623007882695L;
	}
	public static class InvalidAmountException extends RuntimeException {
		private static final long serialVersionUID = 6933588360860874091L;
	}
	public static class OverdraftException extends RuntimeException {
		private static final long serialVersionUID = -8058879747844588872L;
	}
	public static class InvalidRRIDException extends RuntimeException {
		private static final long serialVersionUID = 2814443229414663356L;
	}
	public static class InvalidEIDException extends RuntimeException {
		private static final long serialVersionUID = -5341999009553303770L;
	}
	public static class InvalidUsernamePasswordException extends RuntimeException {
		private static final long serialVersionUID = 5834183009812577348L;
	}
	public static class ExistingEmailException extends RuntimeException {
		private static final long serialVersionUID = -3072784961352175807L;
	}
	public static class ExistingUsernameException extends RuntimeException {
		private static final long serialVersionUID = 519042640220138945L;
	}
	public static class ExistingEmployeeException extends RuntimeException {
		private static final long serialVersionUID = -3219925368288235878L;
	}
	public static class MalformedUsernamePasswordException extends RuntimeException {
		private static final long serialVersionUID = 890900388646858846L;
	}
	public static class MalformedAmountException extends RuntimeException {
		private static final long serialVersionUID = 7023530591971651957L;
	}
}
