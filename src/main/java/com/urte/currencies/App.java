package com.urte.currencies;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App
{
    final static String DELIMITER = " ";

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static Pattern CURRENCY_PATTERN = Pattern.compile("[a-zA-Z]{3}");

    private static String DATE_ERROR = "Enter two dates in format YYYY-MM-DD";
    private static String CURRENCY_ERROR = "Enter at least one valid currency code";

    public static void main( String[] args ) {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter dates");

        String dateInput = in.nextLine();
        List<LocalDate> dates = Arrays
                .stream(dateInput.split(DELIMITER))
                .map(date -> date.trim())
                .filter(date -> DATE_PATTERN.matcher(date).matches())
                .map(date -> LocalDate.parse(date))
                .limit(2)
                .sorted()
                .collect(Collectors.toList());

        dates.forEach(date -> System.out.println("You entered date: " + date.toString()));

        if (dates.size() < 2) {
            exit(DATE_ERROR);
        }

        System.out.println("Please enter currency codes");

        String currencyInput = in.nextLine();
        List<String> currencies = Arrays
                .stream(currencyInput.split(DELIMITER))
                .map(currency -> currency.trim())
                .filter(currency -> CURRENCY_PATTERN.matcher(currency).matches())
                .map(currency -> currency.toUpperCase())
                .collect(Collectors.toList());

        currencies.forEach(currency -> System.out.println("You entered currency: " + currency));

        if(currencies.isEmpty()) {
            exit(CURRENCY_ERROR);
        }

        ExchangeRateService exchangeRateService = new ExchangeRateService();
        exchangeRateService.provideExchangeRateInfo(dates, currencies);
    }

    static void exit(String message) {
        final String ERROR_MESSAGE = String.format(message);
        System.out.println(ERROR_MESSAGE);
        System.exit(1);
    }
}
