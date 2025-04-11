package ru.academits.gerasimenko.CountriesJson.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.academits.gerasimenko.CountriesJson.Country;
import ru.academits.gerasimenko.CountriesJson.Currency;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {
    private static final String COUNTRIES_REFERENCE_TO_NULL_MESSAGE = "Countries List reference to null";

    public static void main(String[] args) {
        final String directoryPath = "src/main/resources/";

        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries;

        try {
            final String inputFileName = "countries.json";
            countries = mapper.readValue(new File(directoryPath + inputFileName), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Failed to read json file: " + e.getMessage());
            return;
        }

        if (countries == null) {
            System.out.println("Error: countries list reference to null");
            return;
        }

        int totalPopulation = getTotalPopulation(countries);
        Set<Currency> currenciesSet = getCurrenciesSet(countries);

        final int minPopulation = 1000000;
        List<Country> searchedCountries = getCountriesWithPopulationOverMinPopulation(countries, minPopulation);

        System.out.println("Total population of all the listed countries is " + totalPopulation + " people.");
        System.out.println("Set of all countries currencies:");

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            System.out.println(mapper.writeValueAsString(currenciesSet));

            final String outputFileName = "countriesWithPopulationOverMillion.json";
            mapper.writeValue(new File(directoryPath + outputFileName), searchedCountries);
        } catch (IOException ex) {
            System.out.println("Failed to output json data: " + ex.getMessage());
        }
    }

    private static void validateForNullReference(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    public static int getTotalPopulation(List<Country> countries) {
        validateForNullReference(countries, COUNTRIES_REFERENCE_TO_NULL_MESSAGE);

        int totalPopulation = 0;

        for (Country country : countries) {
            if (country == null) {
                continue;
            }

            Integer population = country.getPopulation();

            if (population != null) {
                totalPopulation += population;
            }
        }

        return totalPopulation;
    }

    public static Set<Currency> getCurrenciesSet(List<Country> countries) {
        validateForNullReference(countries, COUNTRIES_REFERENCE_TO_NULL_MESSAGE);

        Set<Currency> currenciesSet = new HashSet<>();

        for (Country country : countries) {
            if (country == null) {
                continue;
            }

            List<Currency> currenciesList = country.getCurrencies();

            if (currenciesList == null) {
                continue;
            }

            for (Currency currency : currenciesList) {
                if (currency != null) {
                    currenciesSet.add(currency);
                }
            }
        }

        return currenciesSet;
    }

    public static List<Country> getCountriesWithPopulationOverMinPopulation(List<Country> countries, int minPopulation) {
        validateForNullReference(countries, COUNTRIES_REFERENCE_TO_NULL_MESSAGE);

        List<Country> searchedCountries = new LinkedList<>();

        for (Country country : countries) {
            if (country == null) {
                continue;
            }

            Integer population = country.getPopulation();

            if (population != null && population >= minPopulation) {
                searchedCountries.add(country);
            }
        }

        return searchedCountries;
    }
}