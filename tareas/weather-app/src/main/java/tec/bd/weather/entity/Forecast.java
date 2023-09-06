package tec.bd.weather.entity;

import java.util.Date;

public class Forecast {

    private Integer id;

    private float temperature;

    private Date forecastDate;

    private String countryName;

    private String cityName;

    private String zipCode;

    public Forecast() { }

    public Forecast(String countryName, String cityName, String zipCode, Date forecastDate, float temperature) {
        this(0, countryName, cityName, zipCode, forecastDate, temperature);
    }

    public Forecast(Integer id, String countryName, String cityName, String zipCode, Date forecastDate, float temperature) {
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    @Override
    public String toString() {
        return "Forecast {" +
                "id = " + id +
                ", temperature = " + temperature +
                ", date = '" + forecastDate + '\'' +
                ", country = '" + countryName + '\'' +
                ", city = '" + cityName + '\'' +
                ", zipCode = '" + zipCode + '\'' +
                ", temperature = " + temperature +
                '}';
    }

    public static void validate(Forecast forecast) {
        if (forecast == null) {
            throw new RuntimeException("No forecast forecast was provided");
        }
        if (forecast.getId() == null) {
            throw new RuntimeException("No forecast forecast ID was provided");
        }

        if (forecast.getCountryName().isBlank()) {
            throw new RuntimeException("Weather forecast country invalid");
        }
        if (forecast.getCityName().isBlank()) {
            throw new RuntimeException("Weather forecast city invalid");
        }
        if (forecast.getZipCode().isBlank()) { // se podria utilizar un Regex
            throw new RuntimeException("Weather forecast zip code invalid");
        }
        if (forecast.getTemperature() < 0) {
            throw new RuntimeException("Weather forecast temperature invalid");
        }
        if (forecast.getForecastDate() == null) { // se podría validar que la fecha se encuentre dentro de un rango
            throw new RuntimeException("Weather forecast date is invalid");
        }
        if (forecast.getForecastDate().after(new Date(System.currentTimeMillis()))) {
            throw new RuntimeException("Weather forecast date is in the future");
        }
    }
}
