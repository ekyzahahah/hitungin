package com.ifc21.xve.hitungin.hitungin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculator.fxml"));
        Scene root = new Scene(fxmlLoader.load(), 390, 720);
        String css = this.getClass().getResource("assets/css/style.css").toExternalForm();
        root.getStylesheets().add(css);
        stage.setTitle("Hitungin Calculator");
        stage.setScene(root);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}