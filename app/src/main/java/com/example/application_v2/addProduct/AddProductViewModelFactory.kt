package com.example.application_v2.addProduct

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application_v2.database.product.ProductDatabaseDao

class AddProductViewModelFactory(
    private val dataSource: ProductDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddProductViewModel::class.java)) {
            return AddProductViewModel(dataSource, application) as T
        } else {
            throw IllegalAccessException("unknow view model class")
        }
    }
}