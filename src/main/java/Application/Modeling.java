package Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Modeling {

    private int countRequests;
    private int countSources;
    private int countDevices;
    private int bufferSize;
    private double lambda;
    private double alpha;
    private double beta;
    private CollectedInformation info;
    private static Modeling instance;

    public Modeling() {
        this.countRequests = 3000;
        this.countSources = 3;
        this.countDevices = 2;
        this.bufferSize = 6;
        this.lambda = 0.7;
        this.alpha = 0.5;
        this.beta = 0.6;
    }

    public void start(){
        double currentTimeSystem = 0.0;
        SourceController src = new SourceController(countSources, lambda);
        BufferingManager bufManager = new BufferingManager(bufferSize);
        ServiceManager serviceManager = new ServiceManager(countDevices, alpha, beta);
        info = new CollectedInformation(countSources, bufferSize, countDevices); //CollectedInformation info
        List<Request> allRequests = new ArrayList<>();
        for (int i = 0; i < countRequests; i++) {
            Request request = src.generateRequest(i);
            allRequests.add(request);
            currentTimeSystem = currentTimeSystem + request.getSourceTimeInterval();
            request.setGenerationTimeRequest(currentTimeSystem);
            info.setSourceGeneratedRequest(request.getSourcePriority(), request.getRequestNumber(), currentTimeSystem);
            List<Request> doneRequests = serviceManager.reduceTimeOnDevices(request.getSourceTimeInterval(), currentTimeSystem);
            for (Request completedReq: doneRequests){
                info.setDeviceReleasedRequest(completedReq.getDeviceNumber(), completedReq.getRequestNumber(), currentTimeSystem);
            }
            for (int j = 0; j < serviceManager.getNumberOfFreeDevices(); j++) {
                if (!bufManager.isBufferEmpty()) {
                    Request tempRequest = bufManager.sendRequestToDevice(currentTimeSystem);
                    serviceManager.serviceRequests(tempRequest, currentTimeSystem);
                    info.setDeviceAcceptedRequest(tempRequest.getDeviceNumber(), tempRequest.getSourcePriority(),
                            tempRequest.getRequestNumber(), tempRequest.getPositionBuffer(), currentTimeSystem);
                } else {
                    break;
                }
            }
            boolean isAccepted = bufManager.addRequestToBuffer(request, currentTimeSystem);
            if (isAccepted) {
                info.setBufferAcceptedRequest(request.getPositionBuffer(), request.getSourcePriority(), request.getRequestNumber(), currentTimeSystem);
            } else {
                info.setBufferRejectedRequest(request.getSourcePriority(), request.getRequestNumber(), currentTimeSystem);
            }

        }

        while ((!bufManager.isBufferEmpty()) || (serviceManager.getNumberOfBusyDevices() != 0)) {
            currentTimeSystem += serviceManager.getMinServiceTime();
            serviceManager.reduceTimeOnDevices(serviceManager.getMinServiceTime(), currentTimeSystem);
            for (int j = 0; j < serviceManager.getNumberOfFreeDevices(); j++) {
                if (!bufManager.isBufferEmpty()) {
                    Request tempRequest = bufManager.sendRequestToDevice(currentTimeSystem);
                    serviceManager.serviceRequests(tempRequest, currentTimeSystem);
                } else {
                    break;
                }
            }
        }
        info.collectData(serviceManager.getCompletedRequests(), bufManager.getRejectedRequests(), new ArrayList<>(Arrays.asList(serviceManager.getWorkingTimesDevices())), currentTimeSystem);

        for (int i = 0; i < src.getCountSources(); i++) {
            System.out.println("------------------------------------------------");
            System.out.println("Data Collection -- Application.Source " + (i+1) );
            info.getSourcesData().get(i).print();
        }
        System.out.println();
        System.out.println("DEVICES");
        for (int i = 0; i < serviceManager.getCountDevices(); i++) {
            System.out.println("------------------------------------------------");
            info.getDevicesData().get(i).print(serviceManager.getCountDevices());
        }
   }
    public static Modeling getInstance() {
        if (instance == null) {
            instance = new Modeling();
        }
        return instance;
    }

    public CollectedInformation getCollectedInfo() {
        return info;
    }

    public void setCountRequests(int countRequests) {
        this.countRequests = countRequests;
    }

    public void setCountSources(int countSources) {
        this.countSources = countSources;
    }

    public void setCountDevices(int countDevices) {
        this.countDevices = countDevices;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public int getCountRequests() {
        return countRequests;
    }

    public int getCountSources() {
        return countSources;
    }

    public int getCountDevices() {
        return countDevices;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public double getLambda() {
        return lambda;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getBeta() {
        return beta;
    }
}