package Application;

import java.util.ArrayList;
import java.util.List;

public class SourceData {
    private Integer countRequests;
    private Integer countCompletedRequests;
    private Integer countRejectedRequests;
    private Double requestsTimeInSystem;
    private Double requestsWaitingTime;
    private Double requestsProcessTime;

    private Double probabilityOfFailure;
    private Double averageTimeSpentInSystem;
    private Double averageWaitingTime;
    private Double averageProcessTime;

    private Double dispersionWaitingTime;
    private Double dispersionProcessTime;
    private Integer sourceIndex;

    public SourceData(Integer sourceIndex, List<Request> completedRequests, List<Request> rejectedRequests) {
        this.sourceIndex = sourceIndex;
        List<Request> totalRequests = new ArrayList<>(completedRequests);
        totalRequests.addAll(rejectedRequests);
        this.countRequests = calcCountRequests(totalRequests);
        this.countCompletedRequests = calcCountRequests(completedRequests);
        this.countRejectedRequests = calcCountRequests(rejectedRequests);
        this.requestsTimeInSystem = calcRequestsTimeInSystem(totalRequests);
        this.requestsWaitingTime = calcRequestsWaitingTime(completedRequests);
        this.requestsProcessTime = calcRequestsProcessTime(completedRequests);

        this.probabilityOfFailure = calcProbabilityOfFailure();
        this.averageTimeSpentInSystem = calcAverageTimeSpentInSystem();
        this.averageWaitingTime = calcAverageWaitingTime();
        this.averageProcessTime = calcAverageProcessTime();

        this.dispersionWaitingTime = calcDispersionWaitingTime(totalRequests);
        this.dispersionProcessTime = calcDispersionProcessTime(completedRequests);
    }

    private int calcCountRequests(List<Request> requests) {
        int count = 0;
        for (Request request : requests) {
            count += 1;
        }
        return count;
    }
    public double calcRequestsTimeInSystem(List<Request> requests){
        Double timeInSystem = 0.0;
        for (Request request : requests) {
            timeInSystem += request.getTimeInSystem();
        }
        return timeInSystem;
    }

    public double calcRequestsWaitingTime( List<Request> requests){
        Double waitingTime = 0.0;
        for (Request request : requests) {
            waitingTime += request.getBufferTimeInterval();
        }
        return waitingTime;
    }

    public double calcRequestsProcessTime(List<Request> requests){
        Double processTime = 0.0;
        for (Request request : requests) {
            processTime += request.getDeviceTimeInterval();
        }
        return processTime;
    }

    public double calcAverageWaitingTime(){
        return requestsWaitingTime / countRequests;
    }
    public double calcDispersionWaitingTime(List<Request> requests){
        Double dispersion = 0.0;
        for (Request request: requests) {
            dispersion += Math.pow(request.getBufferTimeInterval() - averageWaitingTime, 2);
        }
        return (dispersion / requests.size());
    }
    public double calcAverageProcessTime(){
        return requestsProcessTime / countRequests;
    }

    public double calcDispersionProcessTime(List<Request> requests){
        Double dispersion = 0.0;
        for (Request request: requests) {
            dispersion += Math.pow(request.getDeviceTimeInterval() - averageProcessTime, 2);
        }
        return dispersion / requests.size();
    }

    public double calcProbabilityOfFailure(){
        return ((double) countRejectedRequests/countRequests);
    }
    public double calcAverageTimeSpentInSystem(){
        return requestsTimeInSystem / countRequests;
    }
    public void print(){
        System.out.println("Total count of requests: " + countRequests);
        System.out.println("Count of completed requests: " + countCompletedRequests);
        System.out.println("Count of rejected requests: " + countRejectedRequests);
        System.out.println("Total waiting time in buffer of all requests: " + requestsWaitingTime);
        System.out.println("Probability of failure(rejected/total): " + probabilityOfFailure);
        System.out.println("Average time spent in system: " + averageTimeSpentInSystem);
        System.out.println("Average time waiting in buffer: " + averageWaitingTime);
        System.out.println("Average service time on devices: " + averageProcessTime);
        System.out.println("Dispersion of waiting time in buffer: " + dispersionWaitingTime);
        System.out.println("Dispersion of process time on devices: " + dispersionProcessTime);
    }

    public Integer getCountRequests() {
        return countRequests;
    }

    public Integer getCountCompletedRequests() {
        return countCompletedRequests;
    }

    public Integer getCountRejectedRequests() {
        return countRejectedRequests;
    }

    public Double getRequestsTimeInSystem() {
        return requestsTimeInSystem;
    }

    public Double getRequestsWaitingTime() {
        return requestsWaitingTime;
    }

    public Double getRequestsProcessTime() {
        return requestsProcessTime;
    }

    public Double getProbabilityOfFailure() {
        return probabilityOfFailure;
    }

    public Double getAverageTimeSpentInSystem() {
        return averageTimeSpentInSystem;
    }

    public Double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public Double getAverageProcessTime() {
        return averageProcessTime;
    }

    public Double getDispersionWaitingTime() {
        return dispersionWaitingTime;
    }

    public Double getDispersionProcessTime() {
        return dispersionProcessTime;
    }

    public Integer getSourceIndex() {
        return sourceIndex;
    }
}
