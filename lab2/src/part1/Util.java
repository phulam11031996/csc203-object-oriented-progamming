package part1;

import java.util.Map;

public class Util {
    public static int getYearWithHighestEmissions(Country country){
        Map<Integer, Emission> emissions = country.getEmissions();
        double highestEmission = 0.0;
        int highestEmissionYear = 0;
        for (int year : emissions.keySet()){
            double newEmission =
                    emissions.get(year).getN2O() +
                    emissions.get(year).getCO2() +
                    emissions.get(year).getCH4();

            if (highestEmission < newEmission){
                highestEmission = newEmission;
                highestEmissionYear = year;
            }
        }
        return highestEmissionYear;
    }

    public static int getYearWithHighestEmissions(Sector sector){
        Map<Integer, Double> emissions = sector.getEmissions();
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
}
