package se.rydberg.handla.exception;

public class ArticleNotSavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8880109983434869800L;

	private String errorMsg;

	public ArticleNotSavedException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}


}
