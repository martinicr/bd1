package tec.bd.weather;

public class App {
    public static void main( String[] args ) {
        WeatherService weatherService = new WeatherServiceImpl();
        System.out.println(weatherService.getTemperature("Alajuela"));
    }
}
