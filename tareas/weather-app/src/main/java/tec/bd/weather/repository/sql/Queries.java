package tec.bd.weather.repository.sql;

public class Queries {

    public static final String FIND_ALL_FORECAST = "SELECT " +
            "forecast_id, country_name, city_name, zip_code, forecast_date, temperature " +
            "FROM forecast";

    public static final String FIND_FORECAST_BY_ID = "SELECT " +
            "forecast_id, country_name, city_name, zip_code, forecast_date, temperature " +
            "FROM forecast WHERE forecast_id = ?";
    public static final String INSERT_NEW_FORECAST = "INSERT INTO " +
            "forecast(country_name, city_name, zip_code, forecast_date, temperature) " +
            "VALUES(?, ?, ?, ?, ?)";

    public static final String DELETE_FORECAST_BY_ID = "DELETE FROM forecast WHERE forecast_id = ?";

    public static final String UPDATE_FORECAST = "UPDATE forecast SET " +
            "country_name = ?, city_name = ?, zip_code = ?, forecast_date = ?, temperature = ? " +
            "WHERE forecast_id = ?";

}
