package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TransactionRecord {
    @PrimaryKey
    @NonNull
    private String tid;

    @ColumnInfo(name = "kidId")
    private String kidId;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "timestamp")
    private Long timestamp;

    @ColumnInfo(name = "note")
    private String note;

    public String getKidId() {
        return kidId;
    }

    public void setKidId(String kidId) {
        this.kidId = kidId;
    }

    @NonNull
    public String getTid() {
        return tid;
    }

    public void setTid(@NonNull String tid) {
        this.tid = tid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "amount='" + amount + '\'' +
                ", timestamp=" + timestamp +
                ", note='" + note + '\'' +
                '}';
    }
}
