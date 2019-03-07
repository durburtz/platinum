package util;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 5503657216233242367L;
	
	private String header;  // e.g. draw, ping, info
	private String x; // x coord
	private String y; // y coord
	private Object object;  // any additional information that needs attaching
	
	public Message(String header, String x, String y, Object object) {
		super();
		this.header = header;
		this.x = x;
		this.y = y;
		this.object = object;
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getX() {
		return x;
	}
	
	public String getY() {
		return y;
	}
	
	public Object getObject() {
		return object;
	}
	
	public String toString() {
		return header + " " + ", x = " + x + ", y = " + y + ", object = " + object; 
	}
	

}
