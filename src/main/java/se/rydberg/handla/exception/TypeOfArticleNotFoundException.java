package se.rydberg.handla.exception;

public class TypeOfArticleNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7205751910110259016L;
	
	
	private String errorMsg;

	public TypeOfArticleNotFoundException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}


}
