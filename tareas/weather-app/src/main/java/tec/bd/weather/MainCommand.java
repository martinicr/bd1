package tec.bd.weather;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Weather App",
        subcommands = {
                WeatherByCityCommand.class,
                WeatherByZipCodeCommand.class,
                CommandLine.HelpCommand.class
        },
        description = "Weather App Service by City and Zip Code")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
