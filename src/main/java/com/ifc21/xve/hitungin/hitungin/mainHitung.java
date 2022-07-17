package com.ifc21.xve.hitungin.hitungin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class mainHitung extends Application {

    private BigDecimal left;
    private String selectedOperator;
    private boolean numberInputting;

    @FXML
    private TextField display, displayOperator;

    public mainHitung() {
        this.left = BigDecimal.ZERO;
        this.selectedOperator = "";
        this.numberInputting = false;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainHitung.class.getResource("calculator.fxml"));
        Scene root = new Scene(fxmlLoader.load(), 390, 720);
        String css = Objects.requireNonNull(this.getClass().getResource("assets/css/style.css")).toExternalForm();
        root.getStylesheets().add(css);
        stage.setTitle("Hitungin Calculator");
        stage.setScene(root);
        stage.setResizable(false);
        stage.show();
    }

    public void onTargetSelected(ActionEvent e) {
        Button button = (Button)e.getSource();
        final String buttonText = button.getText();
        if (buttonText.equals("C") || buttonText.equals("AC")) {
            if (buttonText.equals("AC")) {
                left = BigDecimal.ZERO;
            }
            selectedOperator = "";
            numberInputting = false;
            displayOperator.setText(null);
            display.setText("0");;
            return;
        }
        if (buttonText.matches("[0-9\\.]")) {
            if (!numberInputting) {
                numberInputting = true;
                display.clear();
            }
            display.appendText(buttonText);
            return;
        }
        if (buttonText.matches("[＋－×÷%]")) {
            left = new BigDecimal(display.getText());
            displayOperator.setText(left + buttonText);
            selectedOperator = buttonText;
            display.setText("0");
            numberInputting = false;
            return;
        }
        if (buttonText.equals("=")) {
            buttonText.replace("+","");
            final BigDecimal right = numberInputting ? new BigDecimal(display.getText()) : left;
            left = calculate(selectedOperator, left, right);
            displayOperator.setText(left.toString());
            display.setText("0");
            numberInputting = false;
        }
    }

    static BigDecimal calculate(String operator, BigDecimal left, BigDecimal right) {
        switch (operator) {
            case "＋":
                return left.add(right);
            case "－":
                return left.subtract(right);
            case "×":
                return left.multiply(right);
            case "÷":
                return left.divide(right);
            case  "%":
                return left.divide(right);
            default:
        }
        return right;
    }

    public static void main(String[] args) {
        launch();
    }
}