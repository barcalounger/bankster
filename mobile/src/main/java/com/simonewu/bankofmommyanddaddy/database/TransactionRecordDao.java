package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TransactionRecordDao {
    @Query("SELECT * FROM transactionRecord")
    List<TransactionRecord> getAll();

    @Query("SELECT * FROM transactionRecord WHERE tid IN (:txids)")
    List<TransactionRecord> loadAllByIds(String[] txids);

    @Query("SELECT * FROM transactionRecord WHERE tid = :txid")
    TransactionRecord loadById(String txid);

    @Query("SELECT * FROM transactionRecord WHERE kidId = :kidId ORDER BY timestamp DESC")
    TransactionRecord loadByKidId(String kidId);

    @Insert
    void insertAll(TransactionRecord... records);

    // TODO: Will I ever actually use this?
    @Delete
    void delete(TransactionRecord user);
}
