package se.rydberg.handla.exception;

public class ShopListNotSavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9151533443457419913L;

	private String errorMsg;

	public ShopListNotSavedException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}
}
