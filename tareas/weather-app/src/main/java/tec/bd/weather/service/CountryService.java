package tec.bd.weather.service;

import tec.bd.weather.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> getAllCountries();

    Optional<Country> getCountryById(int countryId);

    Country newCountry(String countryName);

    void removeByCountryId(int countryId);

    Country updateCountry(Country country);
}
