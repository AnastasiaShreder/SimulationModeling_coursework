package Application;

public class DeviceData {
    private double serviceTime;
    private double allServiceTime;
    private double exploitationRate;
    private int deviceIndex;

    public DeviceData(double serviceTime, double allServiceTime, int deviceIndex) {
        this.serviceTime = serviceTime;
        this.allServiceTime = allServiceTime;
        this.exploitationRate = serviceTime / allServiceTime;
        this.deviceIndex = deviceIndex;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getAllServiceTime() {
        return allServiceTime;
    }

    public double getExploitationRate() {
        return exploitationRate;
    }

    public int getDeviceIndex() {
        return deviceIndex;
    }

    public void print(int countDevices){
        System.out.println("Application.Device№"+ (deviceIndex+1));
        System.out.println("Service time of all devices: "+ allServiceTime);
        System.out.println("Service time: " + serviceTime);
        System.out.println("Exploitation rate: "+ exploitationRate);

        }

}

