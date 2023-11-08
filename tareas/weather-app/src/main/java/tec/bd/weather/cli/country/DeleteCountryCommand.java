package tec.bd.weather.cli.country;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "country-delete", aliases = {"cd"}, description = "Delete new country")
public class DeleteCountryCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<country Id>", description = "The country Id.", defaultValue = "0")
    private int countryId;

    @Override
    public void run() {

        var appContext = new ApplicationContext();
        var countryService = appContext.getCountryService();

        try {
            countryService.removeByCountryId(countryId);
            System.out.println("Country Id :" + countryId + " deleted");
        } catch (Exception e) {
            System.err.println("Country Id :" + countryId + " not deleted. Reason: " + e.getMessage());
        }

    }
}
