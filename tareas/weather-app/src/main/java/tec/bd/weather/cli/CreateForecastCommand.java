package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;

@CommandLine.Command(name = "create-forecast", aliases = {"cf"}, description = "Create new forecast for a city")
public class CreateForecastCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<country name>", description = "The country name")
    private String countryName;

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;
    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The Zip code")
    private String zipCode;

    @CommandLine.Parameters(paramLabel = "<forecast date>", description = "The Forecast date")
    private Date forecastDate;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "Temperature value")
    private float temperature;

    @Override
    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            var forecastToBeCreated = new Forecast(countryName, cityName, zipCode, forecastDate, temperature);
            var newForecast = weatherService.newForecast(forecastToBeCreated);
            System.out.println(newForecast);
        } catch (Exception e) {
            System.err.println("Can't create forecast. " +  e.getMessage());
        }
    }
}
