package com.oceanedit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

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
    public void copyOperation() {
        mainArea.copy();
    }

    @FXML
    public void pasteOperation() {
        mainArea.paste();
    }

    @FXML
    public void openAboutWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.oceanedit/About.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root));
            stage.show();
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