import java.util.List;
import java.util.Map;

public class Country implements GreenhouseGasEmitter {
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

    //---------------------------------------------------------------------
    /* behavior */
    public int getYearWithHighestEmissions(){
        Map<Integer, Emission> emissions = this.getEmissions();
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

    /* static method */
    public static Country countryWithHighestCH4InYear(List<Country> countries, int year){
        double highestCH4 = 0.0;
        Country highestCH4Country = new Country();

        for (Country country : countries){
            double currentEmission = country.getEmissions().get(year).getCH4();
            if (currentEmission > highestCH4){
                highestCH4Country = country;
                highestCH4 = currentEmission;
            }
        }
        System.out.println("The number is " + highestCH4Country.getEmissions().get(2000).getCH4());
        return highestCH4Country;
    }

        public static Country countryWithHighestChangeInEmissions(List<Country> countries, int startYear, int endYear) {
        double highestEmission = 0.0;
        double emissionChange = 0;
        Country highestEmissionCountry = new Country();

        for (Country country : countries) {
            double startYearEmission = country.getEmissions().get(startYear).getCO2() +
                    country.getEmissions().get(startYear).getN2O() +
                    country.getEmissions().get(startYear).getCH4();
            double endYearEmission = country.getEmissions().get(endYear).getCO2() +
                    country.getEmissions().get(endYear).getN2O() +
                    country.getEmissions().get(endYear).getCH4();
            emissionChange = endYearEmission - startYearEmission;
            if (emissionChange > highestEmission) {
                highestEmissionCountry = country;
                highestEmission = emissionChange;
            }
        }

        System.out.println("The number is " + highestEmission);
        return highestEmissionCountry;
    }

    public double getEmissionsInYear(int year) {
        return this.emissions.get(year).getCH4() +
                this.emissions.get(year).getCO2() +
                this.emissions.get(year).getN2O();
    }

    public double getTotalEmissionsInYear(int year) {
        return this.emissions.get(year).getCH4() +
                this.emissions.get(year).getCO2() +
                this.emissions.get(year).getN2O();
    }


}
