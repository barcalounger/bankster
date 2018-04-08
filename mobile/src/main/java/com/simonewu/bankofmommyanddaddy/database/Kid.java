package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by simone on 3/23/18.
 */

@Entity
public class Kid {
    @PrimaryKey
    @NonNull
    private String uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "balance_dollars")
    private int dollars;

    @ColumnInfo(name = "balance_cents")
    private int cents;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        while (cents > 100) {
            this.dollars += 1;
            cents -= 100;
        }
        this.cents = cents;
    }

    public String toString() {

        return this.firstName + " - " + this.getBalanceString();
    }

    public String getBalanceString() {
        return "$" + this.getBalanceStringWithoutCurrencySign();
    }

    public String getBalanceStringWithoutCurrencySign() {
        String leadingzero = "";
        if (this.cents < 10) {
            leadingzero = "0";
        }
        return this.dollars + "." + leadingzero + this.cents;
    }
}
