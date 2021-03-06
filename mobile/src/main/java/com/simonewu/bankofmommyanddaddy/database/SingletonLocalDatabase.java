package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by simone on 3/23/18.
 */

public class SingletonLocalDatabase {
    private static AppDatabase instance = null;

    private SingletonLocalDatabase() {       }

    // TODO: use async calls instead so we don't access the database from the main thread
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();
        }
        return instance;
    }
}
