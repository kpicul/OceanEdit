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

package com.oceanedit.Controllers;

import com.oceanedit.FileOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TabPane editorTabs;

    @FXML
    private Label lineNumber;

    @FXML
    private Label columnNumber;

    /**
     * Initializer method.
     */
    @FXML
    public void initialize() {
        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac")) {
            this.menuBar.setUseSystemMenuBar(true);
        }
        addNewTab("new file");
    }

    /**
     * Opens file in the editor.
     */
    @FXML
    public void openFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file");
            File file = fileChooser.showOpenDialog(this.menuBar.getScene().getWindow());
            Tab newTab = addNewTab(file.getName());
            this.editorTabs.getSelectionModel().select(newTab);
            this.getSelectedEditor().openFile(file);
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
            if (this.getSelectedEditor().isNewFile()) {
                saveFileAs();
            }
            else {
                this.getSelectedEditor().saveCurrentFile();
            }
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    @FXML
    public void saveFileAs() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file;");
            File file = fileChooser.showSaveDialog(this.menuBar.getScene().getWindow());
            FileOperations.writeFile(file, getSelectedEditor().getText());
            this.editorTabs.getSelectionModel().getSelectedItem().setText(file.getName());
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
        this.getSelectedEditor().copyOperation();
    }

    /**
     * Executes paste operation.
     */
    @FXML
    public void pasteOperation() {
        this.getSelectedEditor().pasteOperation();
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
     * Adds new editor tab.
     */
    @FXML
    public void addNewEditorTab() {
        Tab newTab = addNewTab("New File");
        this.editorTabs.getSelectionModel().select(newTab);
    }

    /**
     * Quits the application.
     */
    @FXML
    public void quit() {
        System.exit(0);
    }

    private Tab addNewTab(String tabName){
        Tab tab = new Tab(tabName, new Editor());
        this.editorTabs.getTabs().add(tab);
        return tab;
    }

    private Editor getSelectedEditor() {
        return (Editor) this.editorTabs.getSelectionModel().getSelectedItem().getContent();
    }
}