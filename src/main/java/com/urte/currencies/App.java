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
    private static String DATE_ERROR = "Enter at least two dates in format YYYY-MM-DD";

    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter dates");

        String datesInput = in.nextLine();
        List<LocalDate> dates = Arrays
                .stream(datesInput.split(DELIMITER))
                .filter(date -> DATE_PATTERN.matcher(date).matches())
                .map(date -> LocalDate.parse(date))
                .limit(2)
                .sorted()
                .collect(Collectors.toList());
        dates.forEach(date -> System.out.println("You entered date: "+date.toString()));

        if (dates.size() < 2) {
            exit(DATE_ERROR);
        }

        System.out.println("Please enter currency codes");
        String currenciesInput = in.nextLine();
        System.out.println("You entered currencies: "+currenciesInput);
        String[] currencies = currenciesInput.split(DELIMITER);
    }

    static void exit(String message) {
        final String ERROR_MESSAGE = String.format(message);
        System.out.println(ERROR_MESSAGE);
        System.exit(1);
    }
}
