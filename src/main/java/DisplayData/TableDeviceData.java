package DisplayData;

import javafx.beans.property.SimpleStringProperty;

public class TableDeviceData {
    private SimpleStringProperty deviceIndex;
    private SimpleStringProperty timeOfWork;
    private SimpleStringProperty deviceExploitationRate;


    public TableDeviceData(String deviceIndex, String timeOfWork, String deviceExploitationRate) {
        this.deviceIndex = new SimpleStringProperty(deviceIndex);
        this.timeOfWork = new SimpleStringProperty(timeOfWork);
        this.deviceExploitationRate = new SimpleStringProperty(deviceExploitationRate);
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork.set(timeOfWork);
    }

    public void setDeviceExploitationRate(String deviceExploitationRate) {
        this.deviceExploitationRate.set(deviceExploitationRate);
    }

    public void setDeviceIndex(String deviceIndex) {
        this.deviceIndex.set(deviceIndex);
    }

    public String getTimeOfWork() {
        return timeOfWork.get();
    }

    public String getDeviceExploitationRate() {
        return deviceExploitationRate.get();
    }

    public String getDeviceIndex() {
        return deviceIndex.get();
    }
}
