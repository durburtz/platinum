package server;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.Message;

/**
 * @author peter
 *
 */

public class ServerThread implements Runnable {

	private Socket cs; // client socket
	private PlatinumServer server;
	Object readMessage;
	Message castMessage;


	public ServerThread(Socket cs, PlatinumServer server) {
		this.cs = cs;
		this.server = server;
	}

	@Override
	public void run() {

		try {

			ObjectInputStream fromClient = new ObjectInputStream(cs.getInputStream());
			ObjectOutputStream toClient = new ObjectOutputStream(cs.getOutputStream());
			
			try {

				System.out.println("Waiting for a message from " + cs.getInetAddress().toString());
				readMessage = (fromClient.readObject());
				System.out.println("Recieved a new message from " + cs.getInetAddress().toString());
				castMessage = (Message) readMessage;

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (castMessage != null) {
				
				Message recievedMessage = castMessage;
				String msgHeader = recievedMessage.getHeader();
				if (msgHeader.equals("Draw")) {
					System.out.println(cs.getInetAddress().toString() + " sent coords: " + recievedMessage.getX() + " " + recievedMessage.getY()+".");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
