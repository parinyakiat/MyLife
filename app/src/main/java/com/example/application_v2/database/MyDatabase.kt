package com.example.application_v2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.application_v2.database.product.Product
import com.example.application_v2.database.product.ProductDatabaseDao
import com.example.application_v2.database.user.User
import com.example.application_v2.database.user.UserDatabaseDao

@Database(entities = [User::class, Product::class],version = 1,exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract val userDao: UserDatabaseDao
    abstract val gymDao: ProductDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "myDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}