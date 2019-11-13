package com.urte.currencies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.urte.currencies.domain.CurrencyByDate;
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

        String expected = "\nCurrency Name; Code; Rate; Date; Difference;\n" +
                "Australijos doleris; AUD; 1.6087; 2019-11-12; -0.1000;\n";

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

        assertEquals(expected, exchangeRateService.getReport(currenciesInfo));
    }
}
