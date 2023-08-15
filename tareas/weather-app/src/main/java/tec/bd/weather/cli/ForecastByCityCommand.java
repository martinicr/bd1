package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "by-city", aliases = { "bc" }, description = "Get weather for a particular city")
public class ForecastByCityCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @Override
    public void run() {
        System.out.println("By City: " + cityName);

        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            System.out.println(weatherService.getCityTemperature(cityName));
        } catch (Exception e) {
            System.err.println(cityName + " is not supported");
        }
    }
}
