package Application;

import Controllers.AutoModeFormController;
import Controllers.MenuFormController;
import Controllers.ParametersFormController;
import Controllers.StepModeFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        loadMenu();;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuForm.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Menu");
            ((MenuFormController) loader.getController()).setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAutoMode() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoModeForm.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Auto Mode");
            ((AutoModeFormController) loader.getController()).setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadParameters() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ParametersForm.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Parameters");
            ((ParametersFormController) loader.getController()).setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStepMode() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StepModeForm.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Step Mode");
            ((StepModeFormController) loader.getController()).setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
