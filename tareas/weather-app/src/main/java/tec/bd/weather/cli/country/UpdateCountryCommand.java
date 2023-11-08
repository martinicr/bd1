package tec.bd.weather.cli.country;

import picocli.CommandLine;

@CommandLine.Command(name = "country-update", aliases = {"cu"}, description = "Update country")
public class UpdateCountryCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Update country");
    }
}
