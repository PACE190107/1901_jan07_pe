package com.ers.util;

public class ERSExceptions {
	private ERSExceptions() { }
	
	public static class InvalidRRIDException extends RuntimeException {
		private static final long serialVersionUID = 2814443229414663356L;
	}
	public static class InvalidEIDException extends RuntimeException {
		private static final long serialVersionUID = -5341999009553303770L;
	}
	public static class InvalidUsernamePasswordException extends RuntimeException {
		private static final long serialVersionUID = 5834183009812577348L;
	}
	public static class ExistingUsernameException extends RuntimeException {
		private static final long serialVersionUID = 519042640220138945L;
	}
	public static class MalformedUsernamePasswordException extends RuntimeException {
		private static final long serialVersionUID = 890900388646858846L;
	}
	public static class MalformedAmountException extends RuntimeException {
		private static final long serialVersionUID = 7023530591971651957L;
	}
	public static class URINotRecognizedException extends RuntimeException {
		private static final long serialVersionUID = -1321426012117970501L;
	}
	public static class MethodNotImplementedException extends RuntimeException {
		private static final long serialVersionUID = -6118052341161937769L;
	}
}
