package tec.bd.weather.repository.memory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InMemoryForecastRepositoryTest {

    private InMemoryForecastRepository repository;

    @Test
    public void givenInMemoryCollection_whenGetCurrentMaxId_thenReturnMaxId() {
        // Arrange
        this.repository = new InMemoryForecastRepository();

        // Act
        var actual = this.repository.getCurrentMaxId();

        // Assert
        assertThat(actual).isEqualTo(4);
    }
}
