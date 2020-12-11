package Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceManager {
    private List<Device> devices;
    private List<Double> serviceIntervals;
    private List<Request> completedRequests;
    private Double [] workingTimesDevices;
    private int countDevices;
    private double alpha;
    private double beta;

    public ServiceManager(int countDevices, double alpha, double beta) {
        this.countDevices = countDevices;
        this.devices = new ArrayList<>();
        this.serviceIntervals = new ArrayList<>();
        this.completedRequests = new ArrayList<>();
        this.workingTimesDevices = new Double[countDevices];
        Arrays.fill(this.workingTimesDevices, 0.0);
        this.alpha = alpha;
        this.beta = beta;
        for (int i = 0; i < countDevices; i++){
            devices.add(new Device(i+1, alpha, beta));
            serviceIntervals.add(-1.0);
        }
    }

    public double getMinServiceTime(){
        double minTime = Double.MAX_VALUE;
        for (int i = 0; i < devices.size(); i++){
            if(devices.get(i).getServiceTime() > 0) {
                minTime = Math.min(minTime, devices.get(i).getServiceTime());
            }
        }
        return minTime;
    }

    public List<Request> reduceTimeOnDevices(double time, double curTime){
        List<Request> tmpList = new ArrayList<>();
        for (int i = 0; i < countDevices; i++) {
            if (devices.get(i).getServiceTime() > 0) {
                devices.get(i).reduceServiceTime(time);
                if (devices.get(i).getServiceTime() <= 0) {
                    tmpList.add(devices.get(i).freeDevice(curTime));

                }
            }
        }
        completedRequests.addAll(tmpList);
        return tmpList;
    }

    public int getNumberOfFreeDevices(){
        int count = 0;
        for (int i = 0; i < countDevices; i++) {
            if (devices.get(i).getServiceTime() <= 0){
                count++;
            }
        }
        return count;
    }

    public int getNumberOfBusyDevices(){
        int count = 0;
        for (int i = 0; i < countDevices; i++) {
            if (devices.get(i).getServiceTime() > 0){
                count++;
            }
        }
        return count;
    }

    public void serviceRequests(Request request, double curTime){
        for (int i = 0; i < countDevices; i++) {
                if (devices.get(i).getServiceTime() <= 0){
                    devices.get(i).setServiceTime(calcServiceTime());
                    workingTimesDevices[i] += devices.get(i).getServiceTime();
                    request.setDeviceTimeInterval(devices.get(i).getServiceTime());
                    devices.get(i).setRequest(request);
                    request.setArrivalTimeDevice(curTime);
                    request.setDeviceNumber(i);
                    break;
                }
            }
        }

public boolean isBusy(){
    for (int i = 0; i < countDevices; i++) {
        if (devices.get(i).getServiceTime() <= 0){
            return false;
        }
    }
    return true;
}
    public double calcServiceTime(){
        return (alpha + (beta - alpha)*Math.random());
    }

    public int getCountDevices() {
        return countDevices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<Request> getCompletedRequests() {
        return completedRequests;
    }

    public Double[] getWorkingTimesDevices() {
        return workingTimesDevices;
    }
}
