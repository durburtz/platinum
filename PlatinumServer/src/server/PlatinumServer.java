package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlatinumServer {
	
	ServerSocket ss;
	ServerThread st;
	public boolean shouldRun = true; // boolean used to control run() method
	private ServerDrawBox serverDrawBox = new ServerDrawBox(1200, 800); 

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("Please enter in the Terminal: java PlatinumServer <host> <port>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
	
		new PlatinumServer(hostName, portNumber);
		System.out.println("Starting up server");

	}

	/**
	 * The constructor for a CardServer object. It contains a run method that allows new connections from multiple clients to be made.
	 * Each connection is then passed to a new thread in an Executor-made thread pool to service individual client requests.
	 * 
	 * @requires An available host location and port number
	 * @param hostName The location to host the server
	 * @param portNumber The port number used to listen to clients
	 */
	
	public PlatinumServer(String hostName, int portNumber) {

		try {
			
			ss = new ServerSocket(portNumber);
			System.out.println("Server is running and ready for connections!");
			
			while (shouldRun) {
			
				ExecutorService threadPool = Executors.newCachedThreadPool();
				Socket cs = ss.accept();
				System.out.println("New connection to the server from " + cs.getInetAddress().toString());
				ServerThread pt = new ServerThread(cs, this);
				threadPool.execute(pt);

			}
			
			
		} catch (IOException e) {
			System.err.println("Something went wrong, check that the address and Port number are both correct!");
			e.printStackTrace();

		} finally {
			System.out.println("Making final shutdown adjustments - threadpool has been closed and now trying to close ServerSocket");
			if (ss != null) {
				try {
					ss.close();
					System.out.println("ServerSocket has now been closed, OK to exit");
				} catch (IOException e) {
					System.out.println("Unable to close ServerSocket, server still running");
				}
			}
			System.out.println("ServerSocket has already been closed, OK to exit");
		}

	}
	
	public ServerDrawBox getServerDrawBox() {
		return serverDrawBox; 
	}


}
