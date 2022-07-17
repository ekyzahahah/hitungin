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
import java.util.Objects;

public class mainHitung extends Application {
    @FXML
    private TextField previousOperation, currentOperation;

    private String operation;

    private void allClear() {
        this.currentOperation.setText("");
        this.previousOperation.setText("");
        this.operation = null;
    }

    private void selectNumber(String number) {
        if (number.equals(".") && currentOperation.getText().contains(".")) return;
        this.currentOperation.setText(currentOperation.getText() + number);
    }

    private void selectOperation(String operation) {
        if (this.currentOperation.getText().equals("")) return;
        if (!this.previousOperation.getText().equals("")) {
            this.summary();
        }
        this.operation = operation;
        this.previousOperation.setText(this.currentOperation.getText());
        this.currentOperation.setText("");
    }

    private void summary() {
        float sum;
        float prev = Float.parseFloat(previousOperation.getText());
        float current = Float.parseFloat(currentOperation.getText());
        if (Float.isNaN(prev) || Float.isNaN(current)) return;
        switch (this.operation) {
            case "＋":
                sum = prev + current;
                break;
            case "－":
                sum = prev - current;
                break;
            case "x":
                sum = prev * current;
                break;
            case "÷":
                sum = prev / current;
                break;
            default:
                return;
        }
        this.currentOperation.setText(String.valueOf(sum));
        this.operation = null;
        this.previousOperation.setText("");
    }

    private void updateView() {
        this.currentOperation.setText(currentOperation.getText());
        this.previousOperation.setText(this.previousOperation.getText());
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

    public void onNumberSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        selectNumber(button.getText());
        updateView();
    }

    public void onOperationSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        selectOperation(button.getText());
        updateView();
    }

    public void onTargetSelected() {
    }

    public void equalsOperationSelected() {
        summary();
        updateView();
    }

    public void onAllClearSelected() {
        allClear();
    }

    public static void main(String[] args) {
        launch();
    }
}