package com.example.roomsqlmvvm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)

    @Query("SELECT * FROM usersdatav")
    fun getAllItem(): Flow<List<Item>>
}