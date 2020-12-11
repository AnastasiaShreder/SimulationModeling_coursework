package Controllers;

import Application.Main;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuFormController {
    private Main main;
    @FXML
    private Button exitButton;

    public void onClickAutoMode(ActionEvent actionEvent) {
        main.loadAutoMode();
    }

    public void onClickStepMode(ActionEvent actionEvent) {
        main.loadStepMode();
    }

    public void onClickSettingsButton(ActionEvent actionEvent) {
        main.loadParameters();
    }

    public void onClickExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
