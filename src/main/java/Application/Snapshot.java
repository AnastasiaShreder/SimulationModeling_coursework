package Application;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private Double currentTime = 0.0;
    private List<String> sources = new ArrayList<>();
    private List<Pair<String, String>> buffer = new ArrayList<>();
    private List<Pair<String, String>> devices = new ArrayList<>();
    private String stepAction = "";

    public Snapshot(int countSources, int sizeBuffer, int countDevices) {
        for (int i = 0; i < countSources; i++) {
            sources.add("Free");
        }
        for (int i = 0; i < sizeBuffer; i++) {
            buffer.add(new Pair<>("Free", "Null"));
        }
        for (int i = 0; i < countDevices; i++) {
            devices.add(new Pair<>("Free", "Null"));
        }
    }

    public Snapshot(Snapshot snapshot) {
        this.currentTime = snapshot.getCurrentTime();
        this.sources = new ArrayList<>(snapshot.getSources());
        this.buffer = new ArrayList<>(snapshot.getBuffer());
        this.devices = new ArrayList<>(snapshot.getDevices());
        this.stepAction = snapshot.getStepAction();
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public void setSourceAction(int numSource, String numRequest) {
        this.sources.set(numSource, numRequest);
    }

    public void setBufferAction(int positionBuffer, String numRequest, String requestPriority) {
        this.buffer.set(positionBuffer, new Pair<>(numRequest, requestPriority));
    }

    public void setDeviceAction(int positionBuffer, String numRequest, String requestPriority) {
        this.devices.set(positionBuffer, new Pair<>(numRequest, requestPriority));
    }

    public void setStepAction(String stepAction) {
        this.stepAction = stepAction;
    }

    public void deleteRequestFromBuffer(int index) {
        buffer.remove(index);
        buffer.add(new Pair<>("Free", "Null"));
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public List<String> getSources() {
        return sources;
    }

    public List<Pair<String, String>> getBuffer() {
        return buffer;
    }

    public List<Pair<String, String>> getDevices() {
        return devices;
    }

    public String getStepAction() {
        return stepAction;
    }

}
