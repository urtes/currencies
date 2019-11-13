package com.urte.currencies;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateService {

    private final String HEADER = "Currency Name; Code; Rate; Date; Difference;";
    private final String DELIMITER = ";";
    private List<InputStream> currenciesData = new ArrayList<>();
    private List<List<CurrencyByDate>> currenciesInfo = new ArrayList<>();

    DownloadService downloadService = new DownloadService();

    public void provideExchangeRateInfo(List<LocalDate> dates, List<String> currencies) {

        currenciesData = downloadService.downloadCurrenciesData(dates, currencies);
        currenciesInfo = currenciesData
                .stream()
                .map(currencyData -> processData(currencyData))
                .collect(Collectors.toList());
        outputData();
    }

    private List<CurrencyByDate> processData(InputStream currencyData) {

        InputStreamReader inputStreamReader = new InputStreamReader(currencyData);
        BufferedReader bufferedReader = null;
        String line = "";
        List<CurrencyByDate> currencyInfo = new ArrayList<>();

        try {

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                CurrencyByDate currencyByDate = new CurrencyByDate();
                String[] dataByDate = line.split(DELIMITER);
                currencyByDate.setName(dataByDate[0].replace("\"", ""));
                currencyByDate.setCode(dataByDate[1].replace("\"", ""));
                currencyByDate.setCurrentDayRate(Double.parseDouble(dataByDate[2]
                        .replace(",", ".")
                        .replace("\"", "")));
                currencyByDate.setDate(LocalDate.parse(dataByDate[3].replace("\"", "")));

                currencyInfo.add(currencyByDate);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return currencyInfo;
        }
    }

    private void outputData() {
        currenciesInfo.forEach(currencyInfo -> {
            System.out.println(HEADER);
            outputLine(currencyInfo);
        });
    }

    private void outputLine(List<CurrencyByDate> currencyInfo) {
        for (int i = 0; i < currencyInfo.size()-1; i++) {
            double currentDayRate = currencyInfo.get(i).getCurrentDayRate();
            double previousDayRate = currencyInfo.get(i+1).getCurrentDayRate();
            double rateDifference = calculateRateDifference(currentDayRate, previousDayRate);
            System.out.println(String.format("%s; %.4f", currencyInfo.get(i), rateDifference));
        }
    }

    private double calculateRateDifference(double currentDayRate, double previousDayRate) {
        return currentDayRate - previousDayRate;
    }
}
