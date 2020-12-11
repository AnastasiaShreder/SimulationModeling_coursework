package Application;

public class Source {
    private int priority;
    private double interval;
    private double lambda;
    public int requestsCount;

    public Source(int priority, double lambda) {
        this.priority = priority;
        this.lambda = lambda;
    }

    public double getLambda() {
        return lambda;
    }

    public double getInterval() {
        return interval;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public int getPriority() {
        return priority;
    }
}
