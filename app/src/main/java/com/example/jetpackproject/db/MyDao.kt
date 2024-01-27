package com.example.jetpackproject.db;


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface MyDao {

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData(): Flow<List<DBItem>>

    @Query("SELECT * FROM item_table WHERE id = :id")
    fun getData(id: Int): Flow<DBItem>

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item:DBItem):Long

    @Delete
    fun delete(item: DBItem): Int

    //modify
    @Update
    fun update(item: DBItem): Int
}