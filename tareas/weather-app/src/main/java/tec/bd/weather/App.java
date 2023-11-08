package tec.bd.weather;

import picocli.CommandLine;
import tec.bd.weather.cli.MainCommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {

    //

    static {
        try (InputStream is = App.class.getClassLoader().getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args ) {

        LOGGER.log(Level.INFO, "Application startup");
        CommandLine cmd = new CommandLine(new MainCommand());
        cmd.setExecutionStrategy(new CommandLine.RunAll()); // default is RunLast
        cmd.execute(args);

        if (args.length == 0) {
            cmd.usage(System.out);
        }

    }
}
