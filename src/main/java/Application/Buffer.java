package Application;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private int size;
    private List<Request> queue;

    public Buffer(int size) {
        this.queue = new ArrayList<>();
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.queue.add(null);
        }
    }

    public boolean isFull(){
        for (int i = 0; i < size;i++){
            if (queue.get(i) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(){
        for (int i = 0; i < size; i++){
            if (queue.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return size;
    }

    public List<Request> getQueue() {
        return queue;
    }
}
