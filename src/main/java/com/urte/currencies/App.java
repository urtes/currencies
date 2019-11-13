package com.urte.currencies;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter dates");
        String datesInput = in.nextLine();
        System.out.println("You entered string "+datesInput);
        String[] dates = datesInput.split(" ");

        System.out.println("Please enter currency codes");
        String currenciesInput = in.nextLine();
        System.out.println("You entered string "+currenciesInput);
        String[] currencies = currenciesInput.split(" ");
    }
}
