package com.oceanedit.Controllers;

import com.oceanedit.FileOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class EditorController extends HBox {
    // Line number area.
    @FXML
    private TextArea lineNumberArea;

    // Main text area.
    @FXML
    private TextArea mainArea;

    private File file;

    public EditorController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.oceanedit/main-window.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Initializer method.
     */
    @FXML
    public void initialize() {
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
        // sizeTextAreaToText();
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
