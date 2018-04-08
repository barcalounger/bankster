package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by simone on 3/23/18.
 */

@Database(entities = {Kid.class, TransactionRecord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KidDao kidDao();
    public abstract TransactionRecordDao transactionRecordDao();
}
