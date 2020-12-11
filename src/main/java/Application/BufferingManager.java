package Application;

import java.util.ArrayList;
import java.util.List;

public class BufferingManager {
    private Buffer buffer;
    private List<Request> rejectedRequests;
    private List<Double> momentsArrival;
    //private String status;
    private int pointer;

    public BufferingManager(int bufferSize) {
        this.buffer = new Buffer(bufferSize);
        this.rejectedRequests = new ArrayList<>();
        this.momentsArrival = new ArrayList<>();
        this.pointer = 0;
        for (int i = 0; i < buffer.getSize();i++){
            momentsArrival.add(-1.0);
        }
    }

    public boolean addRequestToBuffer(Request request, double time){
        if (!buffer.isFull()) {
            request.setArrivalTimeBuffer(time);
            buffer.getQueue().set(pointer, request);
            request.setStatus("ACCEPTED");
            request.setPositionBuffer(pointer);
            pointer++;
            return true;

        } else {
            request.setArrivalTimeBuffer(time);
            request.setExitTimeBuffer(time);
            rejectedRequests.add(request);
            request.setStatus("REJECTED");
            return false;
        }
    }

    public boolean isBufferEmpty(){
        return buffer.isEmpty();
    }
    public Request sendRequestToDevice(double curTime){
        int numReqToSend = 0;
        for (int i = 0; i < buffer.getSize();i++){
            if (buffer.getQueue().get(i) != null) {
                if (buffer.getQueue().get(i).getSourcePriority() < buffer.getQueue().get(numReqToSend).getSourcePriority()) {
                    numReqToSend = i;
                } else {
                    if (buffer.getQueue().get(i).getSourcePriority() == buffer.getQueue().get(numReqToSend).getSourcePriority()){
                        if(buffer.getQueue().get(i).getArrivalTimeBuffer() < buffer.getQueue().get(numReqToSend).getArrivalTimeBuffer()){
                            numReqToSend = i;
                        }
                    }
                }
            }
        }
        pointer--;
        Request tmpReq = buffer.getQueue().remove(numReqToSend);
        tmpReq.setPositionBuffer(numReqToSend);
        tmpReq.setExitTimeBuffer(curTime);

        buffer.getQueue().add(buffer.getSize() - 1, null);
        return tmpReq;
    }

    public List<Request> getRejectedRequests() {
        return rejectedRequests;
    }

    public Buffer getBuffer() {
        return buffer;
    }

}