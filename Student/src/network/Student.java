package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import controller.ConstantList;
import model.MyThread;

public class Student extends MyThread {

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private ArrayList<String> notifications;
	private ArrayList<String> messages;

	public Student(String ip) {
		super("", ConstantList.SLEEP);
		notifications = new ArrayList<>();
		messages = new ArrayList<>();
		try {
			System.out.println("Conexion iniciada");
			socket = new Socket(ip, 2000);
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMessage(String message) throws IOException {
		messages.add(message);
		output.writeUTF(Request.SEND_MESSAGE.toString());
		output.writeUTF(message);
	}
	
	private void responseManager(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case SEND_MESSAGE:
			notifications.add(input.readUTF());
			break;
		}
	}

	@Override
	public void execute() {
		String response;
		try {
			response = input.readUTF();
			if (response != null) {
				responseManager(response);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<String> getMessages() {
		return messages;
	}
	
	public ArrayList<String> getNotifications() {
		return notifications;
	}
}