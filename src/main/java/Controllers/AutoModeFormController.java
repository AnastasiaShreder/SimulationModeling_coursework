package Controllers;

import Application.DeviceData;
import Application.Main;
import Application.Modeling;
import Application.SourceData;
import DisplayData.TableDeviceData;
import DisplayData.TableSourceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AutoModeFormController {
    private Main main;
    private Modeling modeling;
    private ObservableList<TableSourceData> sourcesData = FXCollections.observableArrayList();
    private ObservableList<TableDeviceData> devicesData = FXCollections.observableArrayList();


    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableView sourceTable;

    @FXML
    private TableColumn<TableSourceData, String> sourceNumberColumn;

    @FXML
    private TableColumn<TableSourceData, String> numberOfRequestsColumn;

    @FXML
    private TableColumn<TableSourceData, String> numberOfCompletedRequestsColumn;

    @FXML
    private TableColumn<TableSourceData, String> numberOfRejectedRequestsColumn;

    @FXML
    private TableColumn<TableSourceData, String> probabilityOfFailureColumn;

    @FXML
    private TableColumn<TableSourceData, String> averageTimeInSystemColumn;

    @FXML
    private TableColumn<TableSourceData, String> averageTimeOfWaitingColumn;

    @FXML
    private TableColumn<TableSourceData, String> averageProcessTimeColumn;

    @FXML
    private TableColumn<TableSourceData, String> dispersionWaitingTimeColumn;

    @FXML
    private TableColumn<TableSourceData, String> dispersionProcessTimeColumn;

    @FXML
    private TableView deviceTable;

    @FXML
    private TableColumn<TableDeviceData, String> deviceNumberColumn;

    @FXML
    private TableColumn<TableDeviceData, String> timeOfWorkColumn;

    @FXML
    private TableColumn<TableDeviceData, String> exploitationRateColumn;

    @FXML
    private Button exitButton;

    @FXML
    private Button startButton;

    @FXML
    public void onClickExitButton(ActionEvent actionEvent) {
        main.loadMenu();
    }

    @FXML
    public void initialize() {
        modeling = Modeling.getInstance();
        sourceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("sourceIndex"));
        numberOfRequestsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfRequests"));
        numberOfCompletedRequestsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDoneRequests"));
        numberOfRejectedRequestsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfRejectedRequests"));
        probabilityOfFailureColumn.setCellValueFactory(new PropertyValueFactory<>("probabilityOfFailure"));
        averageTimeInSystemColumn.setCellValueFactory(new PropertyValueFactory<>("averageTimeSpentInSystem"));
        averageTimeOfWaitingColumn.setCellValueFactory(new PropertyValueFactory<>("averageWaitingTime"));
        averageProcessTimeColumn.setCellValueFactory(new PropertyValueFactory<>("averageProcessTime"));
        dispersionWaitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dispersionWaitingTime"));
        dispersionProcessTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dispersionProcessTime"));

        deviceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("deviceIndex"));
        timeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfWork"));
        exploitationRateColumn.setCellValueFactory(new PropertyValueFactory<>("deviceExploitationRate"));
    }

    @FXML
    public void onClickRun(ActionEvent actionEvent) {
        modeling.start();
        sourcesData.clear();
        devicesData.clear();
        List<SourceData> sourceDataList = modeling.getCollectedInfo().getSourcesData();
        List<DeviceData> deviceDataList = modeling.getCollectedInfo().getDevicesData();
        for (SourceData source: sourceDataList) {
            sourcesData.add(new TableSourceData(source.getSourceIndex().toString(),
                    source.getCountRequests().toString(), source.getCountCompletedRequests().toString(),
                    source.getCountRejectedRequests().toString(),
                    String.format("%.6f", source.getProbabilityOfFailure()),
                    String.format("%.6f", source.getAverageTimeSpentInSystem()),
                    String.format("%.6f", source.getAverageWaitingTime()),
                    String.format("%.6f", source.getAverageProcessTime()),
                    String.format("%.6f", source.getDispersionWaitingTime()),
                    String.format("%.6f", source.getDispersionProcessTime())));
        }
        for (DeviceData device: deviceDataList) {
            devicesData.add(new TableDeviceData(String.valueOf(device.getDeviceIndex()),
                    String.format("%.6f", device.getServiceTime()),
                    String.format("%.6f", device.getExploitationRate())));
        }
        sourceTable.setItems(sourcesData);
        deviceTable.setItems(devicesData);
    }





}
