package part1;

import java.util.Map;

public class Country {
    /* instance variables */
    private String name;
    private Map<Integer, Emission> emissions;

    //---------------------------------------------------------------------
    /* default constructor */
    public Country(){}

    /* regular constructor */
    public Country(String name, Map<Integer, Emission> emissions){
        this.name = name;
        this.emissions = emissions;
    }

    //---------------------------------------------------------------------
    /* getter methods */
    public String getName(){ return this.name; }
    public Map<Integer, Emission> getEmissions(){ return this.emissions; }
}
