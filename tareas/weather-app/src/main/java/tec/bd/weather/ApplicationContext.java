package tec.bd.weather;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.InMemoryForecastRepository;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

public class ApplicationContext {

    private Repository<Forecast, Integer> weatherRepository;

    private WeatherService weatherService;

    public ApplicationContext() {
        initWeatherRepository();
        initWeatherService();
    }

    private void initWeatherRepository() {
        this.weatherRepository = new InMemoryForecastRepository();
    }

    private void initWeatherService() {
        this.weatherService = new WeatherServiceImpl(this.weatherRepository);
    }

    public Repository<Forecast, Integer> getWeatherRepository() {
        return this.weatherRepository;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }

}
