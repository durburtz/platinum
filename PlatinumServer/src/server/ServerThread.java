package server;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

			System.out.println("Established IO streams - running player thread");

			try {

				readMessage = (fromClient.readObject());
				System.out.println("recieved a new message");
				castMessage = (Message) readMessage;

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (readMessage != null) {

				// get coords

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
