package windows;

import client.PlatinumClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Tom
 */
public class LoadingBox extends Application {

	private PlatinumClient client = new PlatinumClient();

	@Override
	public void start(Stage primaryStage) {
		Label connectLabel = new Label();
		connectLabel.setText("Click to connect");
		Button btn = new Button();
		btn.setText("Go");

		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			// connection event
			public void handle(ActionEvent event) {
				try {
					client.connect("127.0.0.1", 1098);

					if (client.isConnected()) {

						primaryStage.setScene(new DrawBox(1280, 750, client).getScene());
					}
				}
				catch (Exception ex) {
					//ex.printStackTrace("Connection to the Server failed...");
				}
				

			}
		});

		connectLabel.relocate(-10, 0);

		Pane topLayer = new Pane();
		topLayer.getChildren().addAll(btn, connectLabel);
		btn.relocate(190, 180);
		connectLabel.relocate(160, 160);

		Scene startScene = new Scene(topLayer, 400, 400);
		
		
		primaryStage.setTitle("Plat lads");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
