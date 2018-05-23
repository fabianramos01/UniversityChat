package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.MyThread;

public class Server extends MyThread {

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Connection> connections;
	private Socket socket;

	public Server() throws IOException {
		super(SERVER, SLEEP);
		serverSocket = new ServerSocket(2000);
		System.out.println("Server create at port 2000");
		start();
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			System.out.println("New connection!");
			connections.add(new Connection(socket));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}