package server;

import java.io.Serializable;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ServerDrawBox implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -192860498158335810L;
	private Pane p;
	private Scene scene;
	public ServerDrawBox(int x, int y) 
	{
		Pane canvasPane = new Pane();
		Scene scene = new Scene(canvasPane, x,y); 
		
	}
	
	public ServerDrawBox getServerDrawBox() {
		return this; 
	}
	
	public Scene getServerDrawBoxScene() {
		return scene;
	}
	
}
