package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by simone on 3/23/18.
 */

@Entity
public class Kid {
    @PrimaryKey
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
        this.cents = cents;
    }
}
