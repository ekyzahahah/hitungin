package com.ifc21.xve.hitungin.hitungin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class mainHitungController {
    @FXML
    private TextField previousOperation, currentOperation;

    private String operation;

    private void update() {
        this.currentOperation.setText(this.currentOperation.getText());
        this.previousOperation.setText(this.previousOperation.getText());
        if (this.operation != null) {
            if (previousOperation.getText().contains(this.operation)) return;
            this.previousOperation.setText(this.previousOperation.getText() + this.operation);
        }
    }

    private void allClear() {
        this.currentOperation.setText("");
        this.previousOperation.setText("");
        this.operation = null;
    }

    public void onAllClearSelected() {
        allClear();
    }

    private void delete() {
        StringBuilder tempString = new StringBuilder(this.currentOperation.getText());
        int deleteIndex = tempString.length() - 1;
        tempString.deleteCharAt(deleteIndex);
        this.currentOperation.setText(String.valueOf(tempString));
    }

    public void onDeleteSelected() {
        delete();
    }

    private void selectNumber(String number) {
        if (number.equals(".") && currentOperation.getText().contains(".")) return;
        if (number.equals("100")) return;
        this.currentOperation.setText(currentOperation.getText() + number);
    }

    public void onNumberSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        selectNumber(button.getText());
        update();
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

    public void onOperationSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        selectOperation(button.getText());
        update();
    }

    private void summary() {
        float sum;
        float current = 0;
        float prev;
        StringBuilder stringBuilder = new StringBuilder(previousOperation.getText());
        if (!this.operation.equals("%")) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            current = Float.parseFloat(this.currentOperation.getText());
        }
        prev = Float.parseFloat(String.valueOf(stringBuilder));
        if (this.operation.equals("%")) {
            current = 100;
        }
        if (Float.isNaN(prev) || Float.isNaN(current)) return;
        switch (this.operation) {
            case "+":
                sum = prev + current;
                break;
            case "-":
                sum = prev - current;
                break;
            case "×":
                sum = prev * current;
                break;
            case "÷":
                sum = prev / current;
                break;
            case "%":
                sum = prev / current;
                break;
            default:
                return;
        }
        this.currentOperation.setText(String.valueOf(sum));
        this.operation = null;
        this.previousOperation.setText("");
    }

    public void equalsOperationSelected() {
        summary();
        update();
    }

    public void onMinSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (button.getText().equals("+/-")) {
            String min = button.getText();
            min = "0";
            selectNumber(min);
            selectOperation("-");
            update();
        }
    }

    public void onPercentSelected(ActionEvent e) {
        Button button = (Button) e.getSource();
        selectOperation(button.getText());
        summary();
    }

}
