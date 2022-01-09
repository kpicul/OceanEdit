package com.oceanedit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Startup class.
 */
public class Startup extends Application {
    /**
     * Startup method.
     * @param stage Main stage.
     * @throws IOException Input output exception.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.oceanedit/main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Main Window");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method.
     * @param args Application arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}