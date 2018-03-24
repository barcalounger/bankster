package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by simone on 3/23/18.
 */

@Dao
public interface KidDao {

    @Query("SELECT * FROM user")
    List<Kid> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<Kid> loadAllByIds(String[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first LIMIT 1")
    Kid findByName(String first);

    @Insert
    void insertAll(Kid... users);

    @Delete
    void delete(Kid user);
}
