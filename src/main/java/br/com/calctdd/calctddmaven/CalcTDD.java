package br.com.calctdd.calctddmaven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Alexandre
 */
public class CalcTDD extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculadora TDD");
        stage.getIcons().add(new Image("/images/app-icon.png"));
        stage.setResizable(false);
        stage.setWidth(355);
        stage.setHeight(530);
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
        root.getStylesheets().add("styles/myStyles.css");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
