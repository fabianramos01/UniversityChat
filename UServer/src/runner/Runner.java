package runner;

import java.io.IOException;

import network.Server;

public class Runner {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}