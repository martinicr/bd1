package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;
import java.util.List;

@CommandLine.Command(name = "all-forecasts", aliases = {"af"}, description = "Get all forecasts")
public class AllForecastsCommand implements Runnable {

    @Override
    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            List<Forecast> forecasts = weatherService.getAllForecasts();
            System.out.println("All Forecasts \n===============================================");
            for(Forecast f : forecasts) {
                System.out.println(f);
            }
        } catch (Exception e) {
            System.err.println("Can't create forecast. " +  e.getMessage());
        }
    }
}
