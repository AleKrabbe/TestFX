package gui;

import br.com.calctdd.calctddmaven.controller.FXMLDocumentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;
/**
 * @author Alexandre
 */
public class CalcTDDTests extends ApplicationTest  {
    
    private static FXMLDocumentController controller;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent mainNode = fxmlLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
        mainNode.getStylesheets().add("styles/myStyles.css");
        primaryStage.setScene(new Scene(mainNode));
        primaryStage.show();
        primaryStage.toFront();
        controller = (FXMLDocumentController) fxmlLoader.getController();
    }
    
    @Test
    public void testPressDigit() {
        TextField result = (TextField) GuiTest.find("#resultTextField");
        clickOn("#nineBtn").clickOn("#plusBtn").clickOn("#twoBtn").clickOn("#equalsBtn");
        assertThat(result.getText(), is("11"));
    }    
    
}
