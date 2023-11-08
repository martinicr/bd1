package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Country;

@CommandLine.Command(name = "country-read", aliases = {"cr"}, description = "Read all countries or country Id.")
public class ReadCountryCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<country Id>", description = "The country Id.", defaultValue = "0")
    private int countryId;

    @Override
    public void run() {
        System.out.println("Read Country. Country Id: " + countryId);

        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        // Es que no se pasó un country Id como parametro al comando
        if (countryId == 0) {
            var countries = countryService.getAllCountries();

            System.out.println("Countries");
            System.out.println("=============================================");
            for (Country c: countries) {
                System.out.println(c.getId() + "\t" + c.getCountryName());
            }
        } else {

            var country = countryService.getCountryById(countryId);
            if (country.isPresent()) {
                System.out.println("Country");
                System.out.println("=============================================");
                System.out.println(country.get().getId() + "\t" + country.get().getCountryName());
            } else {
                System.out.println("Country Id: " + countryId + " not found.");
            }
        }
    }
}
