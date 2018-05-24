package model;

public class Notify {

	private String message;
	private boolean state;
	
	public Notify(String message) {
		super();
		this.message = message;
	}
	
	public void setState() {
		state = true;
	}
	
	public boolean isState() {
		return state;
	}
	
	public String getMessage() {
		return message;
	}
}