package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import model.MyThread;

public class Connection extends MyThread {

	private DataInputStream input;
	private DataOutputStream output;
	private Socket socket;

	public Connection(Socket socket) {
		super("", 1000);
		this.socket = socket;
		try {
			input = new DataInputStream(this.socket.getInputStream());
			output = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		start();
	}

	public void sendMessage(String message) throws IOException {
		output.writeUTF(Request.SEND_MESSAGE.toString());
		output.writeUTF(message);

	}
	
	private void managerRequest(String request) throws IOException {
		switch (Request.valueOf(request)) {
		case SEND_MESSAGE:
			break;
		}
	}

	@Override
	public void execute() {
		String request;
		try {
			request = input.readUTF();
			if (request != null) {
				managerRequest(request);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			stop();
		}
	}
}