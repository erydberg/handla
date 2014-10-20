package se.rydberg.handla.exception;

public class ShopListNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5943580444950021164L;

	private String errorMsg;

	public ShopListNotFoundException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

}
