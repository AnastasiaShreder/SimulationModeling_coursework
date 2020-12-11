package Application;

public class Device {
    private Request request;
    private int priority;
    private double serviceTime;
    private double alpha;
    private double beta;


    public Device(int priority, double alpha, double beta) {
        this.priority = priority;
        this.alpha = alpha;
        this.beta = beta;
    }

    public double reduceServiceTime(double deltaTime){
        serviceTime -= deltaTime;
        return serviceTime;
    }

    public Request freeDevice(double curTime){
        Request tempReq = request;
        tempReq.setExitTimeDevice(curTime);
        request = null;
        return tempReq;
    }
    public void serviceRequest(Request req){}

    public double calcServiceTime(double alpha, double beta){
        return (alpha + (beta - alpha)*Math.random());
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
