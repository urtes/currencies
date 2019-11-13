package com.urte.currencies.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that fetches currency data from external api
 */
public class DownloadService {

    public List<InputStream> downloadCurrenciesData(List<LocalDate> dates, List<String> currencies) {
        List<InputStream> files = new ArrayList<>();
        currencies.forEach(currency -> files.add(downloadCurrencyData(currency, dates)));
        return files;
    }

    private InputStream downloadCurrencyData(String currency, List<LocalDate> dates) {
        String url = makeUrlString(dates, currency);
        try {
            InputStream inputStream = new URL(url).openStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String makeUrlString(List<LocalDate> dates, String currency) {
        String dateToDay = dates.get(1).toString();
        String dateFromDay = getFromDay(dates.get(0)).toString();
        return String.format("https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=%s&ff=1&class=Eu&type=day" +
                "&date_from_day=%s&date_to_day=%s", currency, dateFromDay, dateToDay);
    }

    /**
     * Takes into account only weekends, but not officially stated holidays, since that would require additional
     * service or external library to catch up with non fixed date holidays like Good Friday, etc.
     */
    private LocalDate getFromDay(LocalDate dateFromDay) {
        return dateFromDay.getDayOfWeek() == DayOfWeek.SUNDAY ? dateFromDay.minusDays(2) : dateFromDay.minusDays(1);
    }
}
