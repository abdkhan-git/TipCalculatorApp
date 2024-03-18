package com.example.tipcalculatorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TipCalculatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TipCalculatorApp.class.getResource("TipCalculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // attach scene graph to scene
        stage.setTitle("Tip Calculator"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }

    public static void main(String[] args) {
        // create a TipCalculator object and call its start method
        launch();
    }
}