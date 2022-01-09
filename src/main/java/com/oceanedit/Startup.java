// This file is part of OceanEdit.
//
// OceanEdit is free software: you can redistribute it and/or modify it under the terms of the GNU General
// Public License as published by the FreeSoftware Foundation, either version 2 of the License, or (at
// your option) any later version.
//
// OceanEdit is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with OceanEdit. If not, see
// <https://www.gnu.org/licenses/>.

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