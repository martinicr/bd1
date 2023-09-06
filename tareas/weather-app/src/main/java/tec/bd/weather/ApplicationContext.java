package tec.bd.weather;

import org.sqlite.SQLiteDataSource;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

import javax.sql.DataSource;

public class ApplicationContext {

    private static final String SQLITE_DB_URL = "jdbc:sqlite::resource:sqlite/weather-service.db";
    private DataSource sqliteDataSource;

    private Repository<Forecast, Integer> inMemoryForecastRepository;
    private Repository<Forecast, Integer> sqlForecastRepository;

    private WeatherService weatherService;

    public ApplicationContext() {
        initSqliteDataSource();
        initInMemoryForecastRepository();
        initSQLForecastRepository();
        initWeatherService();
    }

    private void initSqliteDataSource() {
        var sqliteDS = new SQLiteDataSource();
        sqliteDS.setUrl(SQLITE_DB_URL);
        this.sqliteDataSource = sqliteDS;
    }

    private void initInMemoryForecastRepository() {
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }

    private void initSQLForecastRepository() {
        this.sqlForecastRepository = new ForecastRepository(this.sqliteDataSource);
    }

    private void initWeatherService() {
        this.weatherService = new WeatherServiceImpl(this.sqlForecastRepository);
    }

    public Repository<Forecast, Integer> getInMemoryForecastRepository() {
        return this.inMemoryForecastRepository;
    }

    public Repository<Forecast, Integer> getSqlForecastRepository() {
        return this.sqlForecastRepository;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }

}
