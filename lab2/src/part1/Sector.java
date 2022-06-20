package part1;

import java.util.Map;

public class Sector {
    /* instance variables */
    private String name;
    private Map<Integer, Double> emissions;

    //---------------------------------------------------------------------
    /* default constructor */
    public Sector(){}

    /* regular constructor */
    public Sector(String name, Map<Integer, Double> emissions){
        this.name = name;
        this.emissions = emissions;
    }

    //---------------------------------------------------------------------
    /* getter methods */
    public String getName() {return this.name;}
    public Map<Integer, Double> getEmissions() {return this.emissions;}
}
