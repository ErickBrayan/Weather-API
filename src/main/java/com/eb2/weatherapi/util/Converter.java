package com.eb2.weatherapi.util;

public class Converter {

    public static double toCelsius(double fahrenheit) {
        return  (fahrenheit - 32) * 5 / 9;
    }
}
