package br.com.calctdd.calctddmaven.controller;

import calctdd.exceptions.ExpressionNotCompleteException;
import br.com.calctdd.calctddmaven.model.Entry;
import br.com.calctdd.calctddmaven.model.Expression;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Region;

/**
 * @author Alexandre
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField resultTextField;    
    @FXML
    private Button equalsBtn;
    @FXML
    private Button plusBtn;
    @FXML
    private Button minusBtn;
    @FXML
    private Button multBtn;
    @FXML
    private Button divBtn;
    @FXML
    private Button backSpaceBtn;
    
    public static enum Operadores {
        SUM, DIV, SUB, MUL, EQUALS;
    }
    
    private Button lastActiveOperatorBtn;
    private Expression curExpr, lastExpr;
    private boolean shouldResetTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastActiveOperatorBtn = null;
        curExpr = null;
        lastExpr = null;
        shouldResetTextField = false;
        initializeButtonIconsSVG();
        resultTextField.setText("0");
        resultTextField.setTextFormatter(new TextFormatter<>(change -> 
            change.getControlNewText().length() <= 10 ? change : null));
    }
    
    private void initializeButtonIconsSVG(){
        loadSVG(equalsBtn, "equals-button", "icon-equals");   
        loadSVG(plusBtn, "plus-button", "icon-plus");
        loadSVG(minusBtn, "minus-button", "icon-minus");
        loadSVG(multBtn, "times-button", "icon-times");
        loadSVG(divBtn, "divide-button", "icon-divide");
        loadSVG(backSpaceBtn, "backspace-button", "icon-backspace");
    }
    
    private void loadSVG(Button btn, String btnClass, String iconClass){
        btn.getStyleClass().add(btnClass);
        btn.setPickOnBounds(true);
        Region icon = new Region();
        icon.getStyleClass().add(iconClass);
        btn.setGraphic(icon);
    }
    
    //TODO: Refactor
    private void appenToResult(String ch){
        try {
            double result = Double.parseDouble(resultTextField.getText());
            if(result == 0){
                if (resultTextField.getText().contains(".")) {
                    resultTextField.appendText(ch);
                } else {
                    resultTextField.setText(ch);
                }
            } else {
                if (shouldResetTextField) {
                    if (!resultTextField.getText().contains(".") && ".".equals(ch)){
                        resultTextField.appendText(ch);
                    } else {
                        resultTextField.setText(ch);
                    }
                    shouldResetTextField = false;
                } else {
                    if (!(resultTextField.getText().contains(".") && ".".equals(ch))){
                        resultTextField.appendText(ch);
                    } else {
                        resultTextField.setText(ch);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Not able to convert result into string.");
        }
    }
    
    private double getResult(){
        return Double.parseDouble(resultTextField.getText());
    }
    
    private void setResult(Double value){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##########", otherSymbols);
       resultTextField.setText(df.format(value));
    }
    
    //TODO: Refactor
    private void setActiveOperator (Button btn) {
        if (btn == null) {
            if (lastActiveOperatorBtn != null){
                lastActiveOperatorBtn.getStyleClass().remove("active");
                lastActiveOperatorBtn.getStyleClass().add("inactive");
                lastActiveOperatorBtn = null;
            }
        } else {
            if(lastActiveOperatorBtn != null) {
                lastActiveOperatorBtn.getStyleClass().remove("active");
                lastActiveOperatorBtn.getStyleClass().add("inactive");
            }
            btn.getStyleClass().add("active");
            lastActiveOperatorBtn = btn;
        }
    }
    
    //TODO: Refactor
    private void resolveExpression(Operadores OP) {
        shouldResetTextField = true;
        if (curExpr == null) {
            curExpr = new Expression(new Entry(getResult()), OP);
        } else {
            if (curExpr.isComplete()) {
                try {
                    lastExpr = curExpr;
                    double exprResult = curExpr.execute();
                    setResult(exprResult);
                    if (OP == Operadores.EQUALS) {
                        curExpr = new Expression(new Entry(getResult()));
                    } else {
                        curExpr = new Expression(new Entry(getResult()), OP);
                    }
                } catch (ExpressionNotCompleteException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (curExpr.hasLeft()) {
                curExpr.setRight(new Entry(getResult()));
                resolveExpression(OP);
            } else {
                if (OP == Operadores.EQUALS && lastExpr != null) {
                    curExpr.setOperador(lastExpr.getOperador());
                    curExpr.setRight(lastExpr.getRight());
                    resolveExpression(OP);
                } else {
                    curExpr.setLeft(new Entry(getResult()));
                    curExpr.setOperador(OP);
                }
            }
        }
    }
    
    @FXML
    private void handleDigitZeroPress(ActionEvent event) {
        appenToResult("0");
        setActiveOperator(null);
    }
            
    @FXML
    private void handleDigitOnePress() {
        appenToResult("1");
        setActiveOperator(null);
    }
            
    @FXML
    private void handleDigitTwoPress(ActionEvent event) {
        appenToResult("2");
        setActiveOperator(null);
    }
            
    @FXML
    private void handleDigitThreePress(ActionEvent event) {
        appenToResult("3");
        setActiveOperator(null);
    }
            
    @FXML
    private void handleDigitFourPress(ActionEvent event) {
        appenToResult("4");
        setActiveOperator(null);
    }
    
    @FXML
    private void handleDigitFivePress(ActionEvent event) {
        appenToResult("5");
        setActiveOperator(null);
    }
    
    @FXML
    private void handleDigitSixPress(ActionEvent event) {
        appenToResult("6");
        setActiveOperator(null);
    }    
    
    @FXML
    private void handleDigitSevenPress(ActionEvent event) {
        appenToResult("7");
        setActiveOperator(null);
    }
    
    @FXML
    private void handleDigitEightPress(ActionEvent event) {
        appenToResult("8");
        setActiveOperator(null);
    }

    @FXML
    private void handleDigitNinePress(ActionEvent event) {
        appenToResult("9");
        setActiveOperator(null);
    }
    
    @FXML
    private void handleComma(ActionEvent event) {
        if (getResult() == 0) {
            appenToResult("0.");
        } else {
            appenToResult(".");
        }
        setActiveOperator(null);
    }
    
    @FXML
    private void handleDoubleZeroes(ActionEvent event) {
        appenToResult("00");
        setActiveOperator(null);
    }
    
    @FXML
    private void handleDivision(ActionEvent event) {
        setActiveOperator(divBtn);
        resolveExpression(Operadores.DIV);
    }

    @FXML
    private void handleMultiplication(ActionEvent event) {
        setActiveOperator(multBtn);
        resolveExpression(Operadores.MUL);
    }

    @FXML
    private void handleSubtraction(ActionEvent event) {
        setActiveOperator(minusBtn);
        resolveExpression(Operadores.SUB);
    }    
    
    @FXML
    private void handleAddition(ActionEvent event) {
        setActiveOperator(plusBtn);
        resolveExpression(Operadores.SUM);
    }
    
    @FXML
    private void handleEquals(ActionEvent event) {
        resolveExpression(Operadores.EQUALS);
        setActiveOperator(null);
    }

    @FXML
    private void handleBackspace(ActionEvent event) {
        String newText = resultTextField.getText().substring(0, resultTextField.getText().length()-1);
        if (!newText.isEmpty()){
            try {
                double result = Double.valueOf(newText);
                
            } catch (NumberFormatException e) {
                System.err.println("Impossivel converter string em double");
            }
        } else {
            newText = "0";
        }
        resultTextField.setText(newText);
    }

    @FXML
    private void handleClearAll(ActionEvent event) {
        setResult(0.0);
        curExpr = null;
        lastExpr = null;
        shouldResetTextField = false;
        setActiveOperator(null);
    }

    @FXML
    private void handleClearEntry(ActionEvent event) {
        setResult(0.0);
        shouldResetTextField = false;
        setActiveOperator(null);
    }
    
}
