package windows;

import client.PlatinumClient;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import util.Message;

public class DrawBox {
	private Scene cScene;
	private PlatinumClient client;
	private Canvas canvas;
	private GraphicsContext gc; 

	public DrawBox(int x, int y, PlatinumClient pc) {

		this.client = pc;
		
		
		
		Pane canvasPane = new Pane();
		
		canvas = new Canvas(x, y);
		gc = canvas.getGraphicsContext2D(); 
		
		canvasPane.getChildren().add(canvas);
		cScene = new Scene(canvasPane, x, y);
		cScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
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

		cScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				
				draw(mouseEvent.getX(),mouseEvent.getY());
				
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
		
		cScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { 
			   public void handle(MouseEvent mouseEvent) { 
				     
				   draw(mouseEvent.getX(), mouseEvent.getY());   
				   
				   } 
			}); 
			
		
	}

	public Scene getScene() {
		return cScene;
	}
	
	public void draw(double x, double y) 
	{
		gc.setFill(Color.BLACK);
		gc.fillRect(x, y, 3, 3);
		Arc arc = new Arc();
        /*arc.setCenterX(x);
        arc.setCenterY(y);
        arc.setRadiusX(25.0f);
        arc.setRadiusY(25.0f);
        arc.setStartAngle(45.0f);
        arc.setLength(270.0f);
        arc.setType(ArcType.ROUND);
        gc.arc(arc);*/
        
	}
	
	public void fillInGaps(double lastX, double lastY, double xNew, double yNew) {
		double possX; 
		double possY; 
		double length; 
		double height; 
		
		// if you have dragged up
		if(lastX >= xNew) height = lastX - xNew; 
		// if you have dragged down 
		else height = xNew - lastX; 
		// dragged towards left 
		if(lastY >= yNew) length = lastY - yNew; 
		// dragged towards right 
		else length = yNew - lastY; 
		
		
		//gc.fill(lastX, lastY, w, h);
	}
	

}
