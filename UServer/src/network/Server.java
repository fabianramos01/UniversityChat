package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

import model.MyThread;

public class Server extends MyThread implements IObserver{

	private static final String SERVER = "Servidor";
	private static final int SLEEP = 1000;
	private ServerSocket serverSocket;
	private ArrayList<Connection> connections;
	private Socket socket;

	public Server(int port) throws IOException {
		super(SERVER, SLEEP);
		serverSocket = new ServerSocket(port);
		connections = new ArrayList<>();
		ConstantList.LOGGER.log(Level.INFO, "Server create at port " + port);
		start();
	}

	@Override
	public void execute() {
		try {
			socket = serverSocket.accept();
			Connection connection = new Connection(socket);
			ConstantList.LOGGER.log(Level.INFO, "Nueva conexion " + socket.getInetAddress().getHostAddress());
			connection.addObserver(this);
			connections.add(connection);
		} catch (IOException e) {
			ConstantList.LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public void update(String name, String message) {
		ConstantList.LOGGER.log(Level.INFO, "Nuevo mensaje de la ip " + name + " : " + message);
		for (Connection connection : connections) {
			if (!connection.getText().equals(name)) {
				ConstantList.LOGGER.log(Level.INFO, "Mensaje enviado a " + connection.getInetAddress());
				connection.sendMessage(message);
			}
		}
	}
}