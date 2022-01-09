package com.oceanedit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Controller for main window.
 */
public class MainController {
    // Main menu bar.
    @FXML
    private MenuBar menuBar;

    // Line number area.
    @FXML
    private TextArea lineNumberArea;

    // Main text area.
    @FXML
    private TextArea mainArea;

    /**
     * Initializer method.
     */
    @FXML
    public void initialize() {
        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac")) {
            this.menuBar.setUseSystemMenuBar(true);
        }
        this.lineNumberArea.setEditable(false);
        setLineNumbers(1);
        setEvents();
    }

    /**
     * Opens file in the editor.
     */
    @FXML
    public void openFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file");
            File file = fileChooser.showOpenDialog(this.mainArea.getScene().getWindow());
            String data = FileOperations.readFile(file);
            this.mainArea.setText(data);
            setLineNumbers(getLineCount());
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    /**
     * Opens file chooser and saves file.
     */
    @FXML
    public void saveFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file;");
            File file = fileChooser.showSaveDialog(this.mainArea.getScene().getWindow());
            FileOperations.writeFile(file, this.mainArea.getText());
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * Executes copy operation.
     */
    @FXML
    public void copyOperation() {
        this.mainArea.copy();
    }

    /**
     * Executes paste operation.
     */
    @FXML
    public void pasteOperation() {
        this.mainArea.paste();
    }

    /**
     * Opens about window.
     */
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

    /**
     * Quits the application.
     */
    @FXML
    public void quit() {
        System.exit(0);
    }

    private void setEvents() {
        mainArea.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                setLineNumbers(getLineCount());
            }
            else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                setLineNumbers(getLineCount());
            }
        });
    }

    private void setLineNumbers(long numberOfLines) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberOfLines; i++) {
            builder.append(String.format("%d\n", i + 1 ));
        }
        this.lineNumberArea.setText(builder.toString());
        sizeTextAreaToText();
    }

    private long getLineCount() {
        return this.mainArea.getText().chars().filter(ch -> ch == '\n').count() + 1;
    }

    private void sizeTextAreaToText() {
        if (getLineCount() > 10) {
            this.lineNumberArea.setMaxWidth(40);
        }
        else if (getLineCount() > 100) {
            this.lineNumberArea.setMaxWidth(45);
        }
    }
}