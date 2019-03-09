package windows;

import client.PlatinumClient;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.Message;

public class canvas {
	private Scene canvasScene;
	private PlatinumClient client;

	public canvas(int x, int y, PlatinumClient pc) {
		
		this.client = pc;
		
		Pane canvasPane = new Pane();
		canvasScene = new Scene(canvasPane, x, y);
		canvasScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				Message m = new Message("Draw", Double.toString(mouseEvent.getX()), Double.toString(mouseEvent.getY()),
						"ree");

				try {
					client.getOutputStream().writeObject(m);
					client.getOutputStream().flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
	}

	public Scene getScene() {
		return canvasScene;
	}

}
