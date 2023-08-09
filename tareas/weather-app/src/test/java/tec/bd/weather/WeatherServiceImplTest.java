package tec.bd.weather;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WeatherServiceImplTest {

    @Test
    public void GivenACity_WhenValidCity_ThenReturnTemperature() {
        // Arrange
        var weatherService = new WeatherServiceImpl();

        // Act
        var actual = weatherService.getCityTemperature("Alajuela");

        // Assert
        assertThat(actual).isEqualTo(23.0f);
    }

}
