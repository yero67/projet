package serviceException;

import java.io.Serializable;

public class BusinessException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(String msg){
		super(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
