package Application;

import java.util.ArrayList;
import java.util.List;

public class SourceController {
    private List<Source> sources;
    private List<Double> momentsGeneration;
    private int countSources;
    private double lambda;

    public SourceController(int countSources, double lambda){
        this.countSources = countSources;
        this.lambda = lambda;
        this.sources = new ArrayList<>();
        this.momentsGeneration = new ArrayList<>();
        for (int i = 0; i < countSources; i++){
            sources.add(new Source(i+1, lambda));
            momentsGeneration.add(-1.0);
        }
    }

    public Request generateRequest(int number) {
        for (int i = 0; i < countSources; i++) {
            if (momentsGeneration.get(i) <= 0) {
                momentsGeneration.set(i, calcInterval(lambda));
            }
        }
        int minSource = 0;
        double minTime = momentsGeneration.get(0);
        for (int i = 0; i < countSources; i++) {
             if (momentsGeneration.get(i) < minTime){
                 minTime = momentsGeneration.get(i);
                 minSource = i;
             }
        }
        for (int i = 0; i < countSources; i++) {
            momentsGeneration.set(i, (momentsGeneration.get(i)-minTime));
        }
        return new Request(minSource, number, minTime);
    }

    public double calcInterval(double lambda){
        return (- 1/lambda)*Math.log(Math.random());
    }

    public int getCountSources() {
        return countSources;
    }
}
