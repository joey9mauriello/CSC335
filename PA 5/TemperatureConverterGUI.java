package temperatures;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.TemperatureConverter;


/**
 * Joey Mauriello
 */
public class TemperatureConverterGUI extends Application {

	private Label cLabel = new Label("Celcius");
	private Label fLabel = new Label("Farenheit");
	
	private TextField cText = new TextField();
	private TextField fText = new TextField();
	
    @Override
    public void start(Stage stage) {

        stage.setTitle("CtoF FtoC");
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        
        pane.add(cLabel, 2, 1);
        pane.add(fLabel, 2, 2);
        pane.add(cText, 3, 1);
        pane.add(fText, 3, 2);
        
        registerHandlers();
        
        var scene = new Scene(pane, 300, 80);
        stage.setScene(scene);
        stage.show();
    }


	public static void main(String[] args) {
        launch();
    }
	
	private void registerHandlers() {
		cText.setOnAction(new CelHandler());
		fText.setOnAction(new FarHandler());
		
	}
	
	private class CelHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			double c = Double.parseDouble(cText.getText());
			double f = TemperatureConverter.CtoF(c);
			fText.setText("" + f);
		}
		
	}
	
	private class FarHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			double f = Double.parseDouble(fText.getText());
			double c = TemperatureConverter.FtoC(f);
			cText.setText("" + c);
			
		}
		
	}

}