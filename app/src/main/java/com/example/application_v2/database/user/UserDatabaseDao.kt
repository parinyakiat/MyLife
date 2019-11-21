package com.example.application_v2.database.user
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDatabaseDao
{
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM  user WHERE username = :username")
    fun getUser(username:String) : User?

}

