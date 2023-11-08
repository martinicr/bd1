package tec.bd.weather.service;

import tec.bd.weather.entity.Country;
import tec.bd.weather.repository.Repository;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    private Repository<Country, Integer> countryRepository;

    public CountryServiceImpl(Repository<Country, Integer> countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> getAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(int countryId) {
        // Validaciones. El country Id es mayor que cero?

        return this.countryRepository.findById(countryId);
    }

    @Override
    public Country newCountry(String countryName) {
        // No permitir que el country name sea nulo o vacio

        // Validar si el country name ya existe en la base de datos
        // para esto habria que buscar el nombre del pais countryName en la base de datos
        // y ver si existe. Si ya existe no se salva.

        var countryToBeSaved = new Country(null, countryName);
        return this.countryRepository.save(countryToBeSaved);
    }

    @Override
    public void removeByCountryId(int countryId) {
        // Validaciones. El country Id es mayor que cero?
        // lanza una exception

        var countryFromDBOpt = this.getCountryById(countryId);

        if (countryFromDBOpt.isEmpty()) {
            throw new RuntimeException("Country Id: " + countryId + " not found");
        }

        this.countryRepository.delete(countryId);
    }

    @Override
    public Country updateCountry(Country country) {
        // validar si el country.Id existe en la base de datos
        // validar si el nombre del country ya existe en la BD

        var countryUpdated = this.countryRepository.update(country);
        return countryUpdated;
    }


}
