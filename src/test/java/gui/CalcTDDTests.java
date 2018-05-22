package gui;

import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
/**
 * @author Alexandre
 */
public class CalcTDDTests extends ApplicationTest  {
    
    private int a, b;
    private String btnA, btnB;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent mainNode = fxmlLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
        mainNode.getStylesheets().add("styles/myStyles.css");
        primaryStage.setScene(new Scene(mainNode));
        primaryStage.show();
        primaryStage.toFront();
    }
    
    @Before
    public void setUp () throws Exception {
        a = pickRandomNumber();
        b = pickRandomNumber();
        btnA = getBtnID(a);
        btnB = getBtnID(b);
    }
    
    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    
    @Test
    public void testSum() {
        double sum = a + b;
        TextField result = (TextField) GuiTest.find("#resultTextField");
        clickOn("#"+btnA).clickOn("#plusBtn").clickOn("#"+btnB).clickOn("#equalsBtn");
        assertThat(Double.valueOf(result.getText()), is(sum));
    }
    
    @Test
    public void testSub() {
        double sub = a - b;
        TextField result = (TextField) GuiTest.find("#resultTextField");
        clickOn("#"+btnA).clickOn("#minusBtn").clickOn("#"+btnB).clickOn("#equalsBtn");
        assertThat(Double.valueOf(result.getText()), is(sub));
    }
    
    @Test
    public void testMult() {
        double mul = a * b;
        TextField result = (TextField) GuiTest.find("#resultTextField");
        clickOn("#"+btnA).clickOn("#multBtn").clickOn("#"+btnB).clickOn("#equalsBtn");
        assertThat(Double.valueOf(result.getText()), is(mul));
    }
    
        @Test
    public void testDiv() {
        double div = (double)a / (double)b;
        TextField result = (TextField) GuiTest.find("#resultTextField");
        clickOn("#"+btnA).clickOn("#divBtn").clickOn("#"+btnB).clickOn("#equalsBtn");
        assertThat(Double.valueOf(result.getText()), is(div));
    }
    
    private String getBtnID(int number){
        String btnName = "";
        switch (number) {
            case 0:
                btnName = "zeroBtn";
                break;
            case 1:
                btnName = "oneBtn";
                break;
            case 2:
                btnName = "twoBtn";
                break;
            case 3:
                btnName = "threeBtn";
                break;
            case 4:
                btnName = "fourBtn";
                break;
            case 5:
                btnName = "fiveBtn";
                break;
            case 6:
                btnName = "sixBtn";
                break;
            case 7:
                btnName = "sevenBtn";
                break;
            case 8:
                btnName = "eightBtn";
                break;
            case 9:
                btnName = "nineBtn";
                break;
        }
        return btnName;
    }
    
    private int pickRandomNumber() {
        Random generator = new Random();
        return generator.nextInt(10);
    }
    
}
