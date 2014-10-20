package se.rydberg.handla.exception;

public class ArticleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -429581629474907976L;

	private String errorMsg;

	public ArticleNotFoundException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

}
