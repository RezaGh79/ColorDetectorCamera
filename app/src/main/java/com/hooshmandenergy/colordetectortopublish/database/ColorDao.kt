package com.hooshmandenergy.colordetectortopublish.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hooshmandenergy.colordetectortopublish.model.UserColor

@Dao
interface ColorDao {

    @Insert
    suspend fun insertColor(color: UserColor)

    @Insert
    suspend fun insertAllColor(colors: List<UserColor>)

    @Delete
    suspend fun deleteColor(color: UserColor)

    @Query("select * from UserColor")
    fun getAllColor(): LiveData<List<UserColor>>
}