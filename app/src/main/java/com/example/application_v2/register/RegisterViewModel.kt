package com.example.application_v2.register

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.application_v2.database.user.User
import com.example.application_v2.database.user.UserDatabaseDao
import kotlinx.coroutines.*

class RegisterViewModel(val database: UserDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var inputusername = MutableLiveData<String>()
    var inputPassword = MutableLiveData<String>()
    var inputConfirmPassword = MutableLiveData<String>()
    var inputEmail = MutableLiveData<String>()

    private var _gotoLogin = MutableLiveData<Boolean>()
    val gotoLogin: LiveData<Boolean>
        get() = _gotoLogin

    var _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean>
        get() = _showToast

    var _showToastPassword = MutableLiveData<Boolean>()
    val showToastPassword: LiveData<Boolean>
        get() = _showToastPassword

    var _showToastHaveUser = MutableLiveData<Boolean>()
    val showToastHaveUser: LiveData<Boolean>
        get() = _showToastHaveUser

    init {
        Log.i("RegisterViewModel", "ViewModelCreate")
    }

    fun clickButton() {

        uiScope.launch {
            Log.i("RegisterViewModel","Username :"+inputusername.value)
            Log.i("RegisterViewModel","Password :"+inputPassword.value)
            Log.i("RegisterViewModel","Comfirm Password :"+inputConfirmPassword.value)
            Log.i("RegisterViewModel","Email :"+inputEmail.value)
            if (checkMatchNotNull()) {
                _showToast.value = true
            }else if (select() != null){
                Log.i("RegisterViewModel", select().toString())
                _showToastHaveUser.value = true
            } else if (!checkMatchPassword()) {
                _showToastPassword.value = true
            } else {
                var newUser = User(username = inputusername.value ,
                    password = inputPassword.value ,
                    email =  inputEmail.value)
                insert(newUser)
                _gotoLogin.value = true
            }
        }

    }

    private suspend fun insert(user: User){
        withContext(Dispatchers.IO){
            database.insert(user)
        }
    }

    private suspend fun select(): User?{
        return withContext(Dispatchers.IO){
            database.getUser(inputusername.value.toString())
        }
    }

    private fun checkMatchNotNull() = (inputusername.value == null
            || inputPassword.value == null
            || inputConfirmPassword == null
            || inputEmail.value == null)

    private fun checkMatchPassword() = inputPassword.value == inputConfirmPassword.value

    override fun onCleared() {
        Log.i("RegisterViewModel", "ViewModelDestroy")
        super.onCleared()
        viewModelJob.cancel()
    }
}