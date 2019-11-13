package com.urte.currencies;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateServiceTest
{
    @Test
    public void getReportTest()
    {
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        List<CurrencyByDate> currencyByDates = new ArrayList<>();
        currencyByDates.add(new CurrencyByDate("Australijos doleris",
                "AUD",
                1.6087,
                LocalDate.of(2019, 11, 12)));
        currencyByDates.add(new CurrencyByDate("Australijos doleris",
                "AUD",
                1.7087,
                LocalDate.of(2019, 11, 11)));

        List<List<CurrencyByDate>> currenciesInfo = new ArrayList<>();
        currenciesInfo.add(currencyByDates);

        assertTrue(exchangeRateService.getReport(currenciesInfo).contains("-0.100"));
    }
}
