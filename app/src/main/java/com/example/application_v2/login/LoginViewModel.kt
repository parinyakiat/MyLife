package com.example.application_v2.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.application_v2.database.user.User
import com.example.application_v2.database.user.UserDatabaseDao
import kotlinx.coroutines.*

class LoginViewModel(val database: UserDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var inputusername = MutableLiveData<String>()
    var inputPassword = MutableLiveData<String>()

    private var _gotoHome = MutableLiveData<Boolean>()
    val gotoHome: LiveData<Boolean>
        get() = _gotoHome

    var _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean>
        get() = _showToast

    var _showToastHaveUser = MutableLiveData<Boolean>()
    val showToastHaveUser: LiveData<Boolean>
        get() = _showToastHaveUser

    var _showToastCheckPassword = MutableLiveData<Boolean>()
    val showToastCheckPassword: LiveData<Boolean>
        get() = _showToastCheckPassword


    init {
        Log.i("LoginViewModel", "ViewModelCreate")
        _showToast.value = false
    }

    fun clickButton() {
        uiScope.launch {
            Log.i("LoginViewModel","Username :"+inputusername.value)
            Log.i("LoginViewModel","Password :"+inputPassword.value)
            var findUser = select()
            if(checkMatchNotNull()){
                _showToast.value = true
            } else if(findUser == null){
                _showToastHaveUser.value = true
            } else if (checkMatchUser(findUser)){
                _gotoHome.value = true
            }
            else{
                _showToastCheckPassword.value = true
            }
        }
    }

    private fun checkMatchUser(user: User) = (inputusername.value == user.username && inputPassword.value == user.password)

    private fun checkMatchNotNull() = (inputusername.value == null
            || inputPassword.value == null)

    private suspend fun select(): User?{
        return withContext(Dispatchers.IO){
            database.getUser(inputusername.value.toString())
        }
    }

    override fun onCleared() {
        Log.i("LoginViewModel", "ViewModelDestroy")
        super.onCleared()
        viewModelJob.cancel()
    }


}
