package se.rydberg.handla.exception;

public class TypeOfArticleNotDeletedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1322083356651944388L;

	private String errorMsg;

	public TypeOfArticleNotDeletedException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}
}
