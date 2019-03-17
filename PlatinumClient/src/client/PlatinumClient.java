package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.ServerDrawBox;
import util.Message;

public class PlatinumClient {

	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private Socket socket;
	private boolean isConnected = false;
	private boolean recivedServerDrawBox = false;
	private ServerDrawBox serverDrawBox;

	public PlatinumClient() {

	}

	public boolean connect(String IP, int port) {

		try {

			socket = new Socket(IP, port);
			System.out.println("Connected to host");
			isConnected = true;

			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
			toServer.flush();

			
			while (! recivedServerDrawBox) {
				

				try {
					Object readMessage = (fromServer.readObject());
					Message castMessage = (Message) readMessage;
					if (castMessage.getHeader().equals("ServerDrawBox")) {
						serverDrawBox = (ServerDrawBox) castMessage.getObject();
						recivedServerDrawBox = true;
						System.out.println("Recived ServerDrawBox " + serverDrawBox);
					}

				} catch (ClassNotFoundException e) {

				
			}
			
			System.out.println("Connected!");
			isConnected = true;
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
		return isConnected;
	}
		
		

	public boolean isConnected() {
		return isConnected;
	}

	public ObjectOutputStream getOutputStream() {
		return toServer;
	}

	public ObjectInputStream getInputStream() {
		return fromServer;
	}
	
	public ServerDrawBox getServerDrawBox() {
		return serverDrawBox;
	}

}
