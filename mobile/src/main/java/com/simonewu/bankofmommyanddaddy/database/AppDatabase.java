package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by simone on 3/23/18.
 */

public abstract class AppDatabase extends RoomDatabase {
    public abstract KidDao kidDao();
}
