package com.example.application_v2.database.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDatabaseDao {
    @Insert
    fun insert(user: Product)

    @Query("SELECT * FROM  product WHERE codeid = :codeid")
    fun getProduct(codeid:String) : Product?

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>
}