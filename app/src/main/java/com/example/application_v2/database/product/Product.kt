package com.example.application_v2.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true)
    var proId: Int =0 ,

    @ColumnInfo(name = "name_pro")
    var name_pro : String?,


    @ColumnInfo(name = "codeid")
    var codeid : String?,

    @ColumnInfo(name = "type")
    var type : String?
)


