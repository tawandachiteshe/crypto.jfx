package com.dickensdev.cryptojfx.model;

public class CryptoModel {
    private String name;
    private boolean cryptoType;
    private double change;
    private double change1Hour;
    private double change1Week;
    private String originalSymbol;
    private double price;
    private PayloadModel.Logo logo;

    public PayloadModel.Logo getLogo() {
        return logo;
    }
    public String getName() {
        return name;
    }

    public boolean isCryptoType() {
        return cryptoType;
    }

    public double getChange() {
        return change;
    }

    public double getChange1Hour() {
        return change1Hour;
    }

    public double getChange1Weekly() {
        return change1Week;
    }

    public String getOriginalSymbol() {
        return originalSymbol;
    }

    public double getPrice() {
        return price;
    }
}
