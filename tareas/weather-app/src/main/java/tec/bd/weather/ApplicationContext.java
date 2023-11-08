package tec.bd.weather;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sqlite.SQLiteDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import tec.bd.weather.entity.Country;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.sql.CountryRepository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.service.CountryService;
import tec.bd.weather.service.CountryServiceImpl;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

import javax.sql.DataSource;

public class ApplicationContext {

    private static final String SQLITE_DB_URL = "jdbc:sqlite::resource:sqlite/weather-service.db";
    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/weather_service" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String MYSQL_DB_USERNAME = "root";
    private static final String MYSQL_DB_PASSWORD = "rootroot";
    private DataSource sqliteDataSource;

    private DataSource mysqlDataSource;

    private Repository<Forecast, Integer> inMemoryForecastRepository;
    private Repository<Forecast, Integer> sqlForecastRepository;

    private Repository<Country, Integer> sqlCountryRepository;

    private WeatherService weatherService;

    private CountryService countryService;

    public ApplicationContext() {
        initSqliteDataSource();
        initMySqlDataSource();

        initInMemoryForecastRepository();
        initSQLForecastRepository();
        initSQLCountryRepository();

        initWeatherService();
        initCountryService();
    }

    private void initSqliteDataSource() {
        var sqliteDS = new SQLiteDataSource();
        sqliteDS.setUrl(SQLITE_DB_URL);
        this.sqliteDataSource = sqliteDS;
    }

    public void initMySqlDataSource() {
        var mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(MYSQL_DB_URL);
        mysqlDataSource.setUser(MYSQL_DB_USERNAME);
        mysqlDataSource.setPassword(MYSQL_DB_PASSWORD);
        this.mysqlDataSource = mysqlDataSource;
    }

    private void initHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(MYSQL_DB_URL);
        hikariConfig.setUsername(MYSQL_DB_USERNAME);
        hikariConfig.setPassword(MYSQL_DB_PASSWORD);
        hikariConfig.addDataSourceProperty("connectionTestQuery", "SELECT 1");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "4");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.mysqlDataSource = new HikariDataSource(hikariConfig);
    }

    private void initInMemoryForecastRepository() {
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }

    private void initSQLForecastRepository() {
        this.sqlForecastRepository = new ForecastRepository(this.mysqlDataSource);
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

    // Country Service

    private void initSQLCountryRepository() {
        this.sqlCountryRepository = new CountryRepository(this.mysqlDataSource);
    }

    public Repository<Country, Integer> getSqlCountryRepository() {
        return this.sqlCountryRepository;
    }

    private void initCountryService() {
        this.countryService = new CountryServiceImpl(this.sqlCountryRepository);
    }

    public CountryService getCountryService() {
        return this.countryService;
    }

}
