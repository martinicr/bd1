package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class ForecastRepository implements Repository<Forecast, Integer>  {

    private final DataSource dataSource;

    public ForecastRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Forecast> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Forecast> findAll() {

        // JDBC URL for SQLite database in src/main/resources/sqlite

        // 1. Cadena de conexion
        // String url = "jdbc:sqlite::resource:sqlite/weather-service.db";

        List<Forecast> allForecasts = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(Queries.FIND_ALL_FORECAST)) {

            // loop through the result set
            while (rs.next()) {
                var forecast = this.fromResultSet(rs);
                allForecasts.add(forecast);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve forecasts", e);
        }

        return allForecasts;
    }

    @Override
    public Forecast save(Forecast forecast) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_NEW_FORECAST, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, forecast.getCountryName());
            stmt.setString(2, forecast.getCityName());
            stmt.setString(3, forecast.getZipCode());
            stmt.setDate(4, new java.sql.Date(forecast.getForecastDate().getTime()));
            stmt.setFloat(5, forecast.getTemperature());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Get the last inserted ID
                forecast.setId(generatedKeys.getInt(1));
            }
            return forecast;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save forecast", e);
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Forecast update(Forecast source) {
        return null;
    }

    private Forecast fromResultSet(ResultSet rs) throws SQLException {
        var forecastDate = rs.getDate("forecast_date");
        return new Forecast(
                rs.getInt("forecast_id"),
                rs.getString("country_name"),
                rs.getString("city_name"),
                rs.getString("zip_code"),
                new Date(forecastDate.getTime()),
                rs.getFloat("temperature"));
    }

}
