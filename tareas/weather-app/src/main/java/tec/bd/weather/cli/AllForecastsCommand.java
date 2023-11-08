package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CommandLine.Command(name = "all-forecasts", aliases = {"af"}, description = "Get all forecasts")
public class AllForecastsCommand implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(AllForecastsCommand.class.getName());
    @Override
    public void run() {
        LOGGER.log(Level.FINE, "Inside All Forecast Command");
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            List<Forecast> forecasts = weatherService.getAllForecasts();
            System.out.println("All Forecasts \n===============================================");
            for(Forecast f : forecasts) {
                System.out.println(f);
            }
        } catch (Exception e) {
            System.err.println("Exception during get all forecasts: " +  e.getMessage());
        }
    }
}
