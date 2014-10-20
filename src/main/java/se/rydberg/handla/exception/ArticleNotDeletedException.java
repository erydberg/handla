package se.rydberg.handla.exception;

public class ArticleNotDeletedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4113859222315957073L;

	private String errorMsg;

	public ArticleNotDeletedException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}



}
