package com.ifc21.xve.hitungin.hitungin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class mainHitung extends Application {
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

    public static void main(String[] args) {
        launch();
    }
}