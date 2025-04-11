package ru.academits.gerasimenko.CountriesJson;

import java.util.Objects;

public class Currency {
    private String code;
    private String name;
    private String symbol;

    public Currency() {
    }

    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Currency currency = (Currency) object;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        return prime + Objects.hashCode(code);
    }
}