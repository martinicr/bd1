package tec.bd.weather.repository.memory;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import java.util.*;

public class InMemoryForecastRepository implements Repository<Forecast, Integer> {

    private Set<Forecast> inMemoryForecastData;

    public InMemoryForecastRepository() {
        // "inicializando" la base de datos
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new Forecast(1, "Costa Rica", "Alajuela", "10101", new Date(), 23.0f));
        this.inMemoryForecastData.add(new Forecast(2, "Costa Rica", "Cartago", "20201", new Date(), 24.0f));
        this.inMemoryForecastData.add(new Forecast(3, "Costa Rica", "San Jose", "30301", new Date(), 25.0f));
        this.inMemoryForecastData.add(new Forecast(4, "Costa Rica", "Limon", "40401", new Date(), 25.0f));
    }

    public int getCurrentMaxId() {
        return this.inMemoryForecastData
                .stream()
                .max(Comparator.comparing(Forecast::getId))
                .map(Forecast::getId)
                .orElse(0);
    }

    @Override
    public Optional<Forecast> findById(Integer id) {
        return this.inMemoryForecastData
                .stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst();
    }

    @Override
    public List<Forecast> findAll() {
        return new ArrayList<>(this.inMemoryForecastData);
    }

    @Override
    public Forecast save(Forecast forecast) {
        forecast.setId(this.getCurrentMaxId() + 1);
        this.inMemoryForecastData.add(forecast);
        return forecast;
    }

    @Override
    public void delete(Integer id) {
        var weatherToDelete = this.findById(id);
        this.inMemoryForecastData.remove(weatherToDelete.get());
    }

    @Override
    public Forecast update(Forecast source) {
        // source =  new Weather(1, "Alajuela", "10101", 30.0f)
        var current = this.findById(source.getId()).get();
        // current =  new Weather(1, "Alajuela", "10101", 23.0f)

        current.setCountryName(source.getCountryName());
        current.setCityName(source.getCityName());
        current.setZipCode(source.getZipCode());
        current.setTemperature(source.getTemperature());

        // borramos el objeto existente y lo reemplazamos por el actualizado
        this.delete(current.getId());
        this.save(current);

        return current;
        // source =  new Weather(1, "Alajuela", "10101", 30.0f)
    }
}
