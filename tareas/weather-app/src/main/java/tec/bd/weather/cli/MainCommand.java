package tec.bd.weather.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Weather App",
        subcommands = {
                ForecastByCityCommand.class,
                ForecastByZipCodeCommand.class,
                CreateForecastCommand.class,
                UpdateForecastCommand.class,
                CommandLine.HelpCommand.class
        },
        description = "Weather App")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
