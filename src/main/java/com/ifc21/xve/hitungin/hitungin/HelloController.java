package com.ifc21.xve.hitungin.hitungin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onTargetSelected(MouseEvent e) {
        System.out.println(e.getTarget());
    }
}