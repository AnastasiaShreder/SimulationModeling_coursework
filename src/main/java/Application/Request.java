package Application;

public class Request {
    private int sourcePriority;
    private int requestNumber;
    private int deviceNumber;
    private int positionBuffer;
    private double generationTimeRequest;
    private double sourceTimeInterval;
    private double arrivalTimeDevice = -1;
    private double arrivalTimeBuffer;
    private double deviceTimeInterval;
    private double bufferTimeInterval;
    private double exitTimeBuffer;
    private double exitTimeDevice;
    private double timeInSystem;
    private String status;

    public Request(int sourcePriority, int requestNumber, double sourceTimeInterval) {
        this.sourcePriority = sourcePriority;
        this.requestNumber = requestNumber;
        this.sourceTimeInterval = sourceTimeInterval;
    }

    public Request(int sourcePriority, int requestNumber, double sourceTimeInterval, double arrivalTimeBuffer, double deviceTimeInterval, double exitTimeBuffer, double exitTimeDevice) {
        this.sourcePriority = sourcePriority;
        this.requestNumber = requestNumber;
        this.sourceTimeInterval = sourceTimeInterval;
        this.arrivalTimeBuffer = arrivalTimeBuffer;
        this.deviceTimeInterval = deviceTimeInterval;
        this.exitTimeBuffer = exitTimeBuffer;
        this.exitTimeDevice = exitTimeDevice;
    }
    public int getRequestNumber() { return requestNumber; }

    public int getSourcePriority() {
        return sourcePriority;
    }

    public double getSourceTimeInterval() {
        return sourceTimeInterval;
    }

    public double getArrivalTimeBuffer() {
        return arrivalTimeBuffer;
    }

    public double getDeviceTimeInterval() {
        return deviceTimeInterval;
    }

    public double getExitTimeBuffer() {
        return exitTimeBuffer;
    }

    public double getExitTimeDevice() {
        return exitTimeDevice;
    }

    public double getGenerationTimeRequest() {
        return generationTimeRequest;
    }

    public void setSourcePriority(int sourcePriority) {
        this.sourcePriority = sourcePriority;
    }

    public void setSourceTimeInterval(double sourceTimeInterval) {
        this.sourceTimeInterval = sourceTimeInterval;
    }

    public void setArrivalTimeBuffer(double arrivalTimeBuffer) {
        this.arrivalTimeBuffer = arrivalTimeBuffer;
    }

    public void setDeviceTimeInterval(double deviceTimeInterval) {
        this.deviceTimeInterval = deviceTimeInterval;
    }

    public void setExitTimeBuffer(double exitTimeBuffer) {
        this.exitTimeBuffer = exitTimeBuffer;
    }

    public void setExitTimeDevice(double exitTimeDevice) {
        this.exitTimeDevice = exitTimeDevice;
    }

    public void setRequestNumber(int requestNumber) { this.requestNumber = requestNumber; }

    public void setGenerationTimeRequest(double generationTimeRequest) {
        this.generationTimeRequest = generationTimeRequest;
    }

    public double getTimeInSystem() {
        if (arrivalTimeDevice == -1) {
            return exitTimeBuffer - generationTimeRequest;
        } else {
            return exitTimeDevice - generationTimeRequest;
        }
    }

    public double getArrivalTimeDevice() {

        return arrivalTimeDevice;
    }

    public void setArrivalTimeDevice(double arrivalTimeDevice) {
        this.arrivalTimeDevice = arrivalTimeDevice;
    }

    public double getBufferTimeInterval() {
        return (exitTimeBuffer - arrivalTimeBuffer);
    }

    public void setBufferTimeInterval(double bufferTimeInterval) {
        this.bufferTimeInterval = bufferTimeInterval;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPositionBuffer() {
        return positionBuffer;
    }

    public void setPositionBuffer(int positionBuffer) {
        this.positionBuffer = positionBuffer;
    }
}
