package com.urte.currencies;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {

    List<InputStream> currenciesData = new ArrayList<>();
    DownloadService downloadService = new DownloadService();

    public void provideExchangeRateInfo(List<LocalDate> dates, List<String> currencies) {

        currenciesData = downloadService.downloadCurrenciesData(dates, currencies);
        currenciesData.stream().forEach(currencyData -> outputData(currencyData));
    }

    private void outputData(InputStream currencyData) {
        InputStreamReader input = new InputStreamReader(currencyData);
        BufferedReader buffer = null;
        String line = "";
        String csvSplitBy = ";";

        try {

            buffer = new BufferedReader(input);
            while ((line = buffer.readLine()) != null) {
//                String[] room = line.split(csvSplitBy);
//                System.out.println(line);
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
