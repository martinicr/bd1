package tec.bd.weather.service;

import org.junit.jupiter.api.Test;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


public class WeatherServiceImplTest {

    @Test
    public void GivenACity_WhenCityIsSupported_ThenReturnTemperature() {
        // Arrange
        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = mock(Forecast.class); // new Forecast(..,..,..,..,..)

        given(forecast.getCityName()).willReturn("Alajuela");
        given(forecast.getTemperature()).willReturn(23.0f);
        given(forecastRepository.findAll()).willReturn(List.of(forecast));

        // Act
        var actual = weatherService.getCityTemperature("Alajuela");

        // Assert
        verify(forecastRepository, times(1)).findAll();
        verify(forecast, times(1)).getCityName();
        verify(forecast, times(1)).getTemperature();

        assertThat(actual).isEqualTo(23.0f);
    }

    //TODO: Implementar
    @Test
    public void GivenACity_WhenCityIsNotSupported_ThenException() {
        // Arrange
        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findAll()).willReturn(Collections.emptyList());

        // Act
        try {
            weatherService.getCityTemperature("Alajuela");
            fail("We shouldn't reach this line!");
        } catch (Exception e) {

        }

        // Assert
        verify(forecastRepository, times(1)).findAll();
    }

    @Test
    public void GivenAValidForecast_WhenCreateNewForecast_ThenForecastIsCreated() {
        // Arrange
        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findById(anyInt())).willReturn(Optional.empty());

        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f);

        // Act
        weatherService.newForecast(forecast);

        // Assert
        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, times(1)).save(forecast);
    }

    @Test
    public void GivenExistingForecast_WhenCreateNewForecast_ThenServiceException() {
        // Arrange
        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findById(anyInt())).willReturn(Optional.of(new Forecast()));

        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(),  23.0f);

        // Act
        try {
            weatherService.newForecast(forecast);
            fail("We shouldn't reach this line!");
        } catch (Exception e) {

        }

        // Assert
        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, never()).save(forecast);
    }

    // TODO: Si se intenta ingresar un nuevo Forecast con miembros inválidos, el método no debe llamar al repositorio.
    @Test
    public void GivenAInvalidForecast_WhenCreateNewForecast_ThenServiceException() {

    }

    // TODO: prueba unitaria para probar que una actualización es exitosa

    @Test
    public void GivenValidForecast_WhenUpdatingTemperature_ThenNewTemperature() {

        // Arrange
        var currentForecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f);
        var forecastToBeUpdated = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 19.0f);
        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findById(anyInt())).willReturn(Optional.of(currentForecast));
        given(forecastRepository.update(forecastToBeUpdated)).willReturn(forecastToBeUpdated);
        given(forecastRepository.findAll()).willReturn(List.of(currentForecast));

        var weatherService = new WeatherServiceImpl(forecastRepository);

        // Act
        // 1.
        var oldForecast = weatherService.getCityTemperature("Limon");
        // 2.
        var actual = weatherService.updateForecast(forecastToBeUpdated);

        // Assert
        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, times(1)).update(forecastToBeUpdated);

        assertThat(actual).isNotSameAs(oldForecast);

        assertThat(actual.getId()).isEqualTo(5);
        assertThat(actual.getZipCode()).isEqualTo("33122");
        assertThat(actual.getCountryName()).isEqualTo("Costa Rica");
        assertThat(actual.getCityName()).isEqualTo("Limon");
        assertThat(actual.getTemperature()).isEqualTo(19.0f);
    }

    // TODO: prueba unitaria para probar que un Forecast que NO exista no pueda ser actualizado

    // TODO: prueba unitaria para probar que un Forecast que tiene miembros inválidos, el método no debe llamar al repositorio.

    // TODO: prueba unitaria para probar la eliminación exitosa de un Forecast

    // TODO: prueba unitaria para probar que un Forecast ID que NO exista no pueda ser eliminado

}
