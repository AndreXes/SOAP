package org.andrexes.soap.client.service;

import javax.xml.ws.BindingProvider;

import com.cdyne.ws.weatherws.Forecast;
import com.cdyne.ws.weatherws.ForecastReturn;
import com.cdyne.ws.weatherws.Weather;
import com.cdyne.ws.weatherws.WeatherSoap;

public class WeatherService {
    public static void main(final String[] args) {

        final String result = getWeatherForecastForZIP("90210");
        System.out.println(result);
    }

    public static String getWeatherForecastForZIP(final String zipCode) {
        final Weather weather = new Weather();
        final WeatherSoap weatherSoap = weather.getWeatherSoap();

        addBasicAuthentication((BindingProvider) weatherSoap);

        final StringBuilder sb = new StringBuilder();
        final ForecastReturn response = weatherSoap.getCityForecastByZIP(zipCode == null ? "91210" : zipCode);
        sb.append("City: ").append(response.getWeatherStationCity()).append(System.lineSeparator());

        for (final Forecast forecast : response.getForecastResult().getForecast()) {
            sb.append(" Date : " + forecast.getDate());
            sb.append(" Temp : " + forecast.getTemperatures().getMorningLow() + "°/"
                    + forecast.getTemperatures().getDaytimeHigh() + "°");
            sb.append(" Percipitation : " + forecast.getProbabilityOfPrecipiation().getDaytime() + "%");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static void addBasicAuthentication(final BindingProvider portBP) {
        portBP.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "username");
        portBP.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "password");
    }
}
