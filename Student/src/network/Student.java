package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import controller.ConstantList;
import model.MyThread;
import model.Notify;

public class Student extends MyThread {

	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private ArrayList<Notify> notifications;
	private ArrayList<String> messages;

	public Student(String ip, int port) throws IOException {
		super("", ConstantList.SLEEP);
		notifications = new ArrayList<>();
		messages = new ArrayList<>();
		System.out.println("Conexion iniciada");
		socket = new Socket(ip, port);
		output = new DataOutputStream(socket.getOutputStream());
		input = new DataInputStream(socket.getInputStream());
		start();
	}

	public void sendMessage(String message) throws IOException {
		messages.add(message);
		output.writeUTF(Request.SEND_MESSAGE.toString());
		output.writeUTF(message);
	}

	private void responseManager(String response) throws IOException {
		switch (Request.valueOf(response)) {
		case SEND_MESSAGE:
			System.out.println("..");
			notifications.add(new Notify(input.readUTF()));
			break;
		}
	}

	public void changeNotifyState() {
		for (Notify notify : notifications) {
			if (!notify.isState()) {
				notify.setState();
			}
		}
	}

	public int notifications() {
		int n = 0;
		for (Notify notify : notifications) {
			if (!notify.isState()) {
				n++;
			}
		}
		return n;
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

	public ArrayList<Notify> getNotifications() {
		return notifications;
	}
}