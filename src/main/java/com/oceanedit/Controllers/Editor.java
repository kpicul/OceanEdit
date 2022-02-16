package com.oceanedit.Controllers;

import com.oceanedit.FileOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.TwoDimensional.Position;
import org.fxmisc.richtext.model.TwoDimensional.Bias;



public class Editor extends HBox {
    // Main text area.
    @FXML
    private CodeArea mainArea;

    private File file;

    private boolean saved;

    private boolean newFile;

    public Editor() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com.oceanedit/editor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        saved = false;
        newFile = true;
        mainArea.setParagraphGraphicFactory(LineNumberFactory.get(mainArea));
    }

    /**
     * Opens file in the editor.
     */
    public void openFile(File file) throws IOException{
        String data = FileOperations.readFile(file);
        this.mainArea.insertText(0,data);
        this.file = file;
        newFile = false;
        saved = true;
    }

    /**
     * Opens file chooser and saves file.
     */
    public void saveNewFile(File file) throws IOException{
        FileOperations.writeFile(file, this.mainArea.getText());
    }

    public void saveCurrentFile() throws IOException {
        FileOperations.writeFile(this.file, this.mainArea.getText());
        saved = true;
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

    /**
     * Gets the text from the editor
     * @return the text from current editor.
     */
    public String getText() {
        return this.mainArea.getText();
    }

    public boolean isNewFile() {
        return this.newFile;
    }

    public int getRowNumber() {
        return getPosition().getMajor() + 1;
    }

    public int getColumnNumber() {
        return getPosition().getMinor() + 1;
    }

    private Position getPosition() {
        int offset = mainArea.getCaretPosition();

        return mainArea.offsetToPosition(offset, Bias.Backward);
    }
}
