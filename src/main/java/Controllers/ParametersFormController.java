package Controllers;

import Application.Main;
import Application.Modeling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ParametersFormController {

    @FXML
    private VBox vBox;

    @FXML
    private TextField sourcesAmountField;

    @FXML
    private TextField bufferSizeField;

    @FXML
    private TextField devicesAmountField;

    @FXML
    private TextField requestsNumberField;

    @FXML
    private TextField tau1Field;

    @FXML
    private TextField tau2Field;

    @FXML
    private TextField lambdaField;

    @FXML
    private Button saveButton;

    private Main main;

    private Modeling modeling;

    @FXML
    public void initialize() {
        modeling = Modeling.getInstance();
        sourcesAmountField.setText(String.valueOf(modeling.getCountSources()));
        bufferSizeField.setText(String.valueOf(modeling.getBufferSize()));
        devicesAmountField.setText(String.valueOf(modeling.getCountDevices()));
        requestsNumberField.setText(String.valueOf(modeling.getCountRequests()));
        tau1Field.setText(String.valueOf(modeling.getAlpha()));
        tau2Field.setText(String.valueOf(modeling.getBeta()));
        lambdaField.setText(String.valueOf(modeling.getLambda()));
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        try {
            if (sourcesAmountField.getText().equals("") || bufferSizeField.getText().equals("")
                    || devicesAmountField.getText().equals("") || requestsNumberField.getText().equals("")
                    || tau1Field.getText().equals("") || tau2Field.getText().equals("") || lambdaField.getText().equals("")
            ) {
                throw new NumberFormatException();
            }

            int countSources = Integer.parseInt(sourcesAmountField.getText());
            int bufferSize = Integer.parseInt(bufferSizeField.getText());
            int countDevices = Integer.parseInt(devicesAmountField.getText());
            int countRequests = Integer.parseInt(requestsNumberField.getText());
            double alpha = Double.parseDouble(tau1Field.getText());
            double beta = Double.parseDouble(tau2Field.getText());
            double lambda = Double.parseDouble(lambdaField.getText());

            if ((countSources < 1) || (bufferSize < 1) || (countDevices < 1) || (countRequests < 1)
                    || (alpha < 0) || (beta < 0) || (alpha > beta) || lambda < 0
            ) {
                throw new NumberFormatException();
            }

            modeling.setCountSources(countSources);
            modeling.setBufferSize(bufferSize);
            modeling.setCountDevices(countDevices);
            modeling.setCountRequests(countRequests);
            modeling.setAlpha(alpha);
            modeling.setBeta(beta);
            modeling.setLambda(lambda);

            main.loadMenu();

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert!");
            alert.setHeaderText(null);
            alert.setContentText("All parameters must be more than zero and Alpha < Beta.");
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

}