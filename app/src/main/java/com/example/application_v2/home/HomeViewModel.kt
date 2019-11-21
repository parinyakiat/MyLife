package com.example.application_v2.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.application_v2.database.product.ProductDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel(val database: ProductDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val product = database.getAll()

    init {
        Log.i("HomeViewModel", "ViewModelCreate")
    }

    override fun onCleared() {
        Log.i("HomeViewModel", "ViewModelDestroy")
        super.onCleared()
    }
}