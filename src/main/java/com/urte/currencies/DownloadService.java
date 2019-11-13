package com.urte.currencies;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return String.format("https://www.lb.lt/lt/currency/exportlist/?csv=1&currency=%s&ff=1&class=Eu&type=day" +
                "&date_from_day=%s&date_to_day=%s", currency, dates.get(0).minusDays(1l).toString(),
                dates.get(1).toString());
    }
}
