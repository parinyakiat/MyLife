package com.example.application_v2.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application_v2.database.product.ProductDatabaseDao

class HomeViewModelFactory(
    private val dataSource: ProductDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource, application) as T
        } else {
            throw IllegalAccessException("unknow view model class")
        }
    }
}