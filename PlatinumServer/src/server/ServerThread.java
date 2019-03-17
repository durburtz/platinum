package server;

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
	private Object readMessage;
	private Message castMessage;
	private boolean clientHasBox = false;

	public ServerThread(Socket cs, PlatinumServer server) {
		this.cs = cs;
		this.server = server;
	}

	@Override
	public void run() {

		try {

			ObjectInputStream fromClient = new ObjectInputStream(cs.getInputStream());
			ObjectOutputStream toClient = new ObjectOutputStream(cs.getOutputStream());
			
			while (! clientHasBox) {
				Message sendBox = new Message("ServerDrawBox", null, null, server.getServerDrawBox());
				toClient.writeObject(sendBox);
				toClient.flush();
				clientHasBox = true;
			}

			while (true) {

				try {

					System.out.println("Waiting for a message from " + cs.getInetAddress().toString());
					readMessage = (fromClient.readObject());
					castMessage = (Message) readMessage;

				} catch (ClassNotFoundException e) {

				}

				if (readMessage != null) {

					Message recievedMessage = castMessage;
					String msgHeader = recievedMessage.getHeader();
					if (msgHeader.equals("Draw")) {
						System.out.println(cs.getInetAddress().toString() + " sent coords: " + recievedMessage.getX()
								+ " " + recievedMessage.getY() + ".");
					}

				}
			}

		}

		catch (

		IOException e) {
			e.printStackTrace();
		} finally {
			// struff
		}

	}

}
