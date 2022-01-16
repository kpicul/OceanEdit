package com.oceanedit.Controllers;

import com.oceanedit.FileOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

public class Editor extends HBox {
    // Line number area.
    @FXML
    private TextArea lineNumberArea;

    // Main text area.
    @FXML
    private TextArea mainArea;

    private File file;

    public Editor() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.oceanedit/editor.fxml"));
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
    }

    /**
     * Opens file in the editor.
     */
    public void openFile(File file) throws IOException{
        String data = FileOperations.readFile(file);
        this.mainArea.setText(data);
        this.file = file;
    }

    /**
     * Opens file chooser and saves file.
     */
    public void saveNewFile(File file) throws IOException{
        FileOperations.writeFile(file, this.mainArea.getText());
    }

    public void saveCurrentFile() throws IOException {
        FileOperations.writeFile(this.file, this.mainArea.getText());
    }

    /**
     * Executes copy operation.
     */
    public void copyOperation() {
        this.mainArea.copy();
    }

    /**
     * Executes paste operation.
     */
    public void pasteOperation() {
        this.mainArea.paste();
    }

    public boolean fileAlreadyExists() {
        return this.file != null;
    }
}
