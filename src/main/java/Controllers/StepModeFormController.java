package Controllers;

import Application.Modeling;
import Application.Main;
import DisplayData.TableStepMode;
import Application.Snapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StepModeFormController {

    private Main main;
    private Modeling modeling;
    private List<Snapshot> snapshotList;
    private ObservableList<TableStepMode> sourcesData = FXCollections.observableArrayList();
    private ObservableList<TableStepMode> bufferData = FXCollections.observableArrayList();
    private ObservableList<TableStepMode> devicesData = FXCollections.observableArrayList();
    private int current_step = 0;
    private int number_of_steps = 0;
    private double current_time = 0.0;

    @FXML
    private TableView<TableStepMode> sourcesActionsTable;

    @FXML
    private TableColumn<TableStepMode, String> sourcesNumberColumn;

    @FXML
    private TableColumn<TableStepMode, String> sourcesRequestNumberColumn;

    @FXML
    private TableView<TableStepMode> bufferActionsTable;

    @FXML
    private TableColumn<TableStepMode, String> bufferCellsNumberColumn;

    @FXML
    private  TableColumn<TableStepMode, String> bufferRequestNumberColumn;

    @FXML
    private  TableColumn<TableStepMode, String> bufferRequestPriorityColumn;

    @FXML
    private TableView<TableStepMode> devicesActionsTable;

    @FXML
    private  TableColumn<TableStepMode, String> devicesNumberColumn;

    @FXML
    private  TableColumn<TableStepMode, String> deviceRequestNumberColumn;

    @FXML
    private  TableColumn<TableStepMode, String> deviceRequestPriorityColumn;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label stepLabel;

    @FXML
    private Label numberOfStepsLabel;

    @FXML
    private TextArea globalActionTextArea;

    @FXML
    private TextField stepSnapshotTextField;

    @FXML
    private Button changeStepButton;

    @FXML
    private Button stepFrowardButton;

    @FXML
    private Button stepBackwardButton;

    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        modeling = Modeling.getInstance();
        sourcesNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
        sourcesRequestNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Request_number"));

        bufferCellsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
        bufferRequestNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Request_number"));
        bufferRequestPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("Request_priority"));

        devicesNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
        deviceRequestNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Request_number"));
        deviceRequestPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("Request_priority"));
    }

    @FXML
    public void onClickStepForward() {
        if (current_step == number_of_steps - 1) {
            return;
        }
        ++current_step;
        stepLabel.setText(String.valueOf(current_step));
        changeSnapshot();
    }

    @FXML
    public void onClickStepBackward() {
        if (current_step == 0) {
            return;
        }
        --current_step;
        stepLabel.setText(String.valueOf(current_step));
        changeSnapshot();
    }

    @FXML
    public void onClickChangeStep() {
        try {
            int step = Integer.parseInt(stepSnapshotTextField.getText());
            if (step < 0 || step >= number_of_steps) {
                throw  new NumberFormatException();
            }
            current_step = step;
            stepLabel.setText(String.valueOf(current_step));
            changeSnapshot();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert!");
            alert.setHeaderText(null);
            alert.setContentText("Step must be in interval between 0 and " + number_of_steps);
        }
    }


    @FXML
    public void onClickRun(ActionEvent event) {
        modeling.start();
        snapshotList = modeling.getCollectedInfo().getSnapshots();
        System.out.println("-------" + modeling.getBufferSize());
        System.out.println("+++++++" + modeling.getCollectedInfo().getDevicesData().get(0).getAllServiceTime());
        modeling.getCollectedInfo().getSnapshots().forEach(x -> System.out.println(x.getBuffer()));
        cleanFields();
        changeSnapshot();
    }

    @FXML
    public void onClickExitButton(ActionEvent actionEvent) {
        main.loadMenu();
    }

    private void changeSnapshot() {
        Snapshot current_snapshot = snapshotList.get(current_step);
        System.out.println(snapshotList.get(current_step).getBuffer());

        for (int i = 0; i < sourcesData.size(); i++) {
            sourcesData.set(i, new TableStepMode(String.valueOf(i), current_snapshot.getSources().get(i)));
        }

        for (int i = 0; i < bufferData.size(); i++) {
            bufferData.set(i, new TableStepMode(String.valueOf(i), current_snapshot.getBuffer().get(i).getKey(),
                    current_snapshot.getBuffer().get(i).getValue()));
        }

        for (int i = 0; i < devicesData.size(); i++) {
            devicesData.set(i, new TableStepMode(String.valueOf(i), current_snapshot.getDevices().get(i).getKey(),
                    current_snapshot.getDevices().get(i).getValue()));
        }

        current_time = current_snapshot.getCurrentTime();
        currentTimeLabel.setText(String.valueOf(current_time));
        globalActionTextArea.setText(current_snapshot.getStepAction());
    }

    private void cleanFields() {
        current_step = 0;
        number_of_steps = snapshotList.size();
        current_time = 0.0;
        numberOfStepsLabel.setText(String.valueOf(number_of_steps));

        sourcesData.clear();
        bufferData.clear();
        devicesData.clear();

        for (int i = 0; i < modeling.getCountSources(); i++) {
            sourcesData.add(new TableStepMode(String.valueOf(i), "Free"));
        }
        for (int i = 0; i < modeling.getBufferSize(); i++) {
            bufferData.add(new TableStepMode(String.valueOf(i), "Free", "Null"));
        }
        for (int i = 0; i < modeling.getCountDevices(); i++) {
            devicesData.add(new TableStepMode(String.valueOf(i), "Free", "Null"));
        }
        sourcesActionsTable.setItems(sourcesData);
        bufferActionsTable.setItems(bufferData);
        devicesActionsTable.setItems(devicesData);
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
