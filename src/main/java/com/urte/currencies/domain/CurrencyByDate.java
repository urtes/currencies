package com.urte.currencies.domain;

import java.time.LocalDate;

public class CurrencyByDate {

    private String name;
    private  String code;
    private double currentDayRate;
    private LocalDate date;

    public CurrencyByDate(String name, String code, double currentDayRate, LocalDate date) {
        this.name = name;
        this.code = code;
        this.currentDayRate = currentDayRate;
        this.date = date;
    }

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
