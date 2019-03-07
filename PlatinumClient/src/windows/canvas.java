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
	private PlatinumClient pc;

	public canvas(int x, int y, PlatinumClient pc) {
		Pane canvasPane = new Pane();
		canvasScene = new Scene(canvasPane, x, y);
		this.pc = pc;
		canvasScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				Message m = new Message("Draw", Double.toString(mouseEvent.getX()), Double.toString(mouseEvent.getY()),
						"ree");

				try {
					pc.getOutputStream().writeObject(m);
					pc.getOutputStream().flush();
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
