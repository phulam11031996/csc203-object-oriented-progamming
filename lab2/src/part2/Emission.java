package part2;

public class Emission {

    /* instance variables */
    private double co2;
    private double n2o;
    private double ch4;

    //---------------------------------------------------------------------
    /* default constructor */
    public Emission(){}

    /* regular constructor */
    public Emission(double co2, double n2o, double ch4){
        this.co2 = co2;
        this.n2o = n2o;
        this.ch4 = ch4;
    }

    //---------------------------------------------------------------------
    /* getter methods */
    public double getCO2(){ return this.co2; }
    public double getN2O(){ return this.n2o; }
    public double getCH4(){ return this.ch4; }
}
