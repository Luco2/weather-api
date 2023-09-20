package com.weatherapi.api;

public class Model {

    private String city;
    private String country;
    private double temperatureCelsius;
    private double temperatureFahrenheit;
    private double windMph;
    private double windKph;
    private int windDegree;
    private String windDirection;
    private double precipitationMm;
    private double precipitationInches;
    private int humidity;
    private int cloud;
    private double feelsLikeCelsius;
    private double feelsLikeFahrenheit;

    public Model() {
    }

    public Model(String city, String country, double temperatureCelsius, double temperatureFahrenheit,
                       double windMph, double windKph, int windDegree, String windDirection,
                       double precipitationMm, double precipitationInches, int humidity, int cloud,
                       double feelsLikeCelsius, double feelsLikeFahrenheit) {
        this.city = city;
        this.country = country;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.windMph = windMph;
        this.windKph = windKph;
        this.windDegree = windDegree;
        this.windDirection = windDirection;
        this.precipitationMm = precipitationMm;
        this.precipitationInches = precipitationInches;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelsLikeCelsius = feelsLikeCelsius;
        this.feelsLikeFahrenheit = feelsLikeFahrenheit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public void setTemperatureFahrenheit(double temperatureFahrenheit) {
        this.temperatureFahrenheit = temperatureFahrenheit;
    }

    public double getWindMph() {
        return windMph;
    }

    public void setWindMph(double windMph) {
        this.windMph = windMph;
    }

    public double getWindKph() {
        return windKph;
    }

    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getPrecipitationMm() {
        return precipitationMm;
    }

    public void setPrecipitationMm(double precipitationMm) {
        this.precipitationMm = precipitationMm;
    }

    public double getPrecipitationInches() {
        return precipitationInches;
    }

    public void setPrecipitationInches(double precipitationInches) {
        this.precipitationInches = precipitationInches;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public double getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public void setFeelsLikeCelsius(double feelsLikeCelsius) {
        this.feelsLikeCelsius = feelsLikeCelsius;
    }

    public double getFeelsLikeFahrenheit() {
        return feelsLikeFahrenheit;
    }

    public void setFeelsLikeFahrenheit(double feelsLikeFahrenheit) {
        this.feelsLikeFahrenheit = feelsLikeFahrenheit;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", temperatureCelsius=" + temperatureCelsius +
                ", temperatureFahrenheit=" + temperatureFahrenheit +
                ", windMph=" + windMph +
                ", windKph=" + windKph +
                ", windDegree=" + windDegree +
                ", windDirection='" + windDirection + '\'' +
                ", precipitationMm=" + precipitationMm +
                ", precipitationInches=" + precipitationInches +
                ", humidity=" + humidity +
                ", cloud=" + cloud +
                ", feelsLikeCelsius=" + feelsLikeCelsius +
                ", feelsLikeFahrenheit=" + feelsLikeFahrenheit +
                '}';
    }
}

