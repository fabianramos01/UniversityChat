package controller;

import javax.swing.ImageIcon;

public enum Command {

	COMMAND_MESSAGE("COMMAND_MESSAGE", "Enviar mensaje" , ""),
	CHANGE_IP("CHANGE_IP", "Cambiar la IP" , "");
	
	private String command;
	private String title;
	private String pathImg;
	
	private Command(String command, String title, String pathImg) {
		this.command = command;
		this.title = title;
		this.pathImg = pathImg;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ImageIcon getImg() {
		return new ImageIcon(getClass().getResource(pathImg));
	}
}