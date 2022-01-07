package com.oceanedit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainController {
    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea lineNumberArea;

    @FXML
    private TextArea mainArea;

    @FXML
    public void initialize() {
        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac")) {
            menuBar.setUseSystemMenuBar(true);
        }
        lineNumberArea.setEditable(false);
    }

    @FXML
    public void openFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file");
            File file = fileChooser.showOpenDialog(mainArea.getScene().getWindow());
            String data = FileOperations.readFile(file);
            mainArea.setText(data);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    @FXML
    public void saveFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file;");
            File file = fileChooser.showSaveDialog(mainArea.getScene().getWindow());
            FileOperations.writeFile(file, mainArea.getText());
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    @FXML
    public void quit() {
        System.exit(0);
    }
}