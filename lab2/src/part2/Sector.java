package part2;

import java.util.List;
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
    public String getName() { return this.name; }
    public Map<Integer, Double> getEmissions(){ return this.emissions; }

    //---------------------------------------------------------------------
    /* behavior */
    public int getYearWithHighestEmissions(){
        Map<Integer, Double> emissions = this.getEmissions();
        double highestEmission = 0.0;
        int highestEmissionYear = 0;
        for (int year : emissions.keySet()){
            if (highestEmission < emissions.get(year)){
                highestEmission = emissions.get(year);
                highestEmissionYear = year;
            }
        }
        return highestEmissionYear;
    }

        public static Sector sectorWithHighestAverageEmissions(List<Sector> sectors, int startYear, int endYear){
        double highestEmission = 0.0;
        Sector highestEmissionSector = new Sector();

        for (Sector sector : sectors){
            double totalEmission = 0.0;
            for (int i = startYear; i <= endYear; i++){
                totalEmission += sector.getEmissions().get(i);
            }
            if (totalEmission > highestEmission){
                highestEmissionSector = sector;
                highestEmission = totalEmission;
            }
        }
        System.out.println("The number is " + highestEmission / (endYear - startYear + 1));
        return highestEmissionSector;
    }
}
