package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;
/**
 * @author Alexandre
 */
public class CalcTDDTests extends ApplicationTest  {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainNode = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
        mainNode.getStylesheets().add("styles/myStyles.css");
        primaryStage.setScene(new Scene(mainNode));
        primaryStage.show();
        primaryStage.toFront();
    }
    
    @Test
    public void testPressDigit() {
        clickOn("#nineBtn");
    }    
    
}
