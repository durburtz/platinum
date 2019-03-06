package server;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 5503657216233242367L;
	
	private String header; // the header of the message, e.g. login, play, attack 
	private String message; // the contents of the message
	private Object object;  // any additional information that needs attaching
	
	public Message(String header, String message, Object object) {
		super();
		this.header = header;
		this.message = message;
		this.object = object;
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getObject() {
		return object;
	}
	

}
