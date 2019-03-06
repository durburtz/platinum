package windows;

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
    
    @Override
    public void start(Stage primaryStage) {
        Label connectLabel = new Label(); 
        connectLabel.setText("Click to connect");
        Button btn = new Button();
        btn.setText("Go");
        
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //connection event 
            public void handle(ActionEvent event) {
                
            }
        });
        

  
        connectLabel.relocate(-10, 0); 
        
        
        Pane topLayer = new Pane();
        topLayer.getChildren().addAll(btn, connectLabel); 
        btn.relocate(190, 180);
        connectLabel.relocate(160, 160);
        
        
        
        
        Scene scene = new Scene(topLayer, 400, 400);
        
        primaryStage.setTitle("Plat lads");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
