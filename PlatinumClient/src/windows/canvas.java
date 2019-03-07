package windows;

import java.io.IOException;

import client.PlatinumClient;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.Message;

public class canvas {
	private Scene canvasScene;
	private PlatinumClient client;

	public canvas(int x, int y) {
		Pane canvasPane = new Pane();
		canvasScene = new Scene(canvasPane, x, y);

		canvasScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				System.out.println("X poss: " + mouseEvent.getX() + "Y poss:" + mouseEvent.getY());
				
				Message m = new Message("Draw", Double.toString(mouseEvent.getX()), Double.toString(mouseEvent.getY()),
						"ree");
				
				try {
					client.getOutputStream().writeObject(m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public Scene getScene() {
		return canvasScene;
	}

}
