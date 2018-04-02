package com.simonewu.bankofmommyanddaddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by simone on 3/23/18.
 */

@Dao
public interface KidDao {

    @Query("SELECT * FROM kid")
    List<Kid> getAll();

    @Query("SELECT * FROM kid WHERE uid IN (:userIds)")
    List<Kid> loadAllByIds(String[] userIds);

    @Query("SELECT * FROM kid WHERE uid = :userId")
    Kid loadById(String userId);

    @Query("SELECT * FROM kid WHERE first_name LIKE :first LIMIT 1")
    Kid findByName(String first);

    @Insert
    void insertAll(Kid... users);

    @Update
    void update(Kid user);

    @Delete
    void delete(Kid user);
}
