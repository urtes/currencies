package com.urte.currencies;

import java.time.LocalDate;

public class CurrencyByDate {

    private String name;
    private  String code;
    private double currentDayRate;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCurrentDayRate() {
        return currentDayRate;
    }

    public void setCurrentDayRate(double currentDayRate) {
        this.currentDayRate = currentDayRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s; %s; %.4f; %s;", name, code, currentDayRate, date);
    }
}
