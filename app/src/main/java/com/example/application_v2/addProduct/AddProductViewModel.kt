package com.example.application_v2.addProduct

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.application_v2.database.product.Product
import com.example.application_v2.database.product.ProductDatabaseDao
import kotlinx.coroutines.*

class AddProductViewModel(val database: ProductDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var inputNameProduct = MutableLiveData<String>()
    var inputid = MutableLiveData<String>()
    var inputtype = MutableLiveData<String>()

    private var _gotoHome = MutableLiveData<Boolean>()
    val gotoHome: LiveData<Boolean>
        get() = _gotoHome

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    init {
        Log.i("AddProductViewModel", "ViewModelCreate")
    }


    fun pickimg() {
        uiScope.launch {
            Log.i("AddProductViewModel","Name Product :"+inputNameProduct.value)
            Log.i("AddProductViewModel","ID :"+inputid.value)
            Log.i("AddProductViewModel","Type :"+inputtype.value)
            if (checkMatchNotNull()){
                Log.i("AddProductViewModel","get Product all "+showAll())
                _showSnackbarEvent.value = true
            }else{
                var newGym = Product(name_pro = inputNameProduct.value ,
                    codeid =  inputid.value ,
                    type = inputtype.value)
                insert(newGym)
                Log.i("AddProductViewModel","get Product all "+showAll().toString())
                _gotoHome.value = true
            }
        }
    }

    fun clickButton() {
        uiScope.launch {
            Log.i("AddProductViewModel","Name Product :"+inputNameProduct.value)
            Log.i("AddProductViewModel","ID :"+inputid.value)
            Log.i("AddProductViewModel","Type :"+inputtype.value)
            if (checkMatchNotNull()){
                Log.i("AddProductViewModel","get Product all "+showAll())
                _showSnackbarEvent.value = true
            }else{
                var newGym = Product(name_pro = inputNameProduct.value ,
                    codeid =  inputid.value ,
                    type = inputtype.value)
                insert(newGym)
                Log.i("AddProductViewModel","get Product all "+showAll().toString())
                _gotoHome.value = true
            }
        }
    }

    private suspend fun showAll(): LiveData<List<Product>> {
        return withContext(Dispatchers.IO){
            database.getAll()
        }
    }

    private suspend fun insert(product: Product){
        withContext(Dispatchers.IO){
            database.insert(product)
        }
    }

    private fun checkMatchNotNull() = (inputNameProduct.value == null
            || inputid.value == null
            || inputtype.value == null)

    override fun onCleared() {
        Log.i("RegisterViewModel", "ViewModelDestroy")
        super.onCleared()
        viewModelJob.cancel()
    }
}