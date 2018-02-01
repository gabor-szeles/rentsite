package com.codecool.rentsite.rentable;

import javax.persistence.Embeddable;
import java.util.Currency;

@Embeddable
public class Price {

    private int amount;
    private Currency currency;

    public Price(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Price() {
    }

    public String getFullPrice(){
        return Integer.toString(this.getAmount()) + " " + this.getCurrency().toString();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
