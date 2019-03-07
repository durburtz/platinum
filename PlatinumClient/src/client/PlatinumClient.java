package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlatinumClient {

	public ObjectOutputStream toServer;
	public ObjectInputStream fromServer;
	public Socket socket;

	public PlatinumClient() {

	}

	public boolean connect(String IP, int port) {

		try {

			socket = new Socket(IP, port);
			System.out.println("Connected to host");

			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			toServer.flush();
			toServer.writeObject("REEEE");
			toServer.flush();
			
			System.out.println("Connected!");

			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}

}
