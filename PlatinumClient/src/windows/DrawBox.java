package windows;

import client.PlatinumClient;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.Message;

public class DrawBox {
	private Scene canvasScene;
	private PlatinumClient client;
	Canvas canvas;

	public DrawBox(int x, int y, PlatinumClient pc) {

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

		canvasScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				double previousX;
				double previousY;

				System.out.println("Actual = " + mouseEvent.getX() + ", " + mouseEvent.getY());
				
				Message m = new Message("Draw", Double.toString(mouseEvent.getX()), Double.toString(mouseEvent.getY()),
						"ree");

				try {
					client.getOutputStream().writeObject(m);
					client.getOutputStream().flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				previousX = mouseEvent.getX();
				previousY = mouseEvent.getY();

				
				System.out.println("Previous = " + previousX + ", " + previousY);

			}
		});
	}

	public Scene getScene() {
		return canvasScene;
	}

}
