package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.cli.country.CreateCountryCommand;
import tec.bd.weather.cli.country.ReadCountryCommand;
import tec.bd.weather.cli.country.DeleteCountryCommand;
import tec.bd.weather.cli.country.UpdateCountryCommand;

@CommandLine.Command(
        name = "Weather App",
        subcommands = {
                ForecastByCityCommand.class,
                ForecastByZipCodeCommand.class,
                CreateForecastCommand.class,
                UpdateForecastCommand.class,
                RemoveForecastCommand.class,
                CommandLine.HelpCommand.class,
                AllForecastsCommand.class,
                // Country related Commands
                CreateCountryCommand.class,
                DeleteCountryCommand.class,
                ReadCountryCommand.class,
                UpdateCountryCommand.class
        },
        description = "Weather App")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
