package kr.or.ddit.board;

public class BoardException extends RuntimeException{

	public BoardException() {
		super();
	}

	public BoardException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BoardException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BoardException(String arg0) {
		super(arg0);
	}

	public BoardException(Throwable arg0) {
		super(arg0);
	}
	
}
