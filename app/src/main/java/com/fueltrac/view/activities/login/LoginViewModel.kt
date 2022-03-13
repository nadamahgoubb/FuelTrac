package com.fueltrac.view.activities.login

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.model.Repository
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.model.entities.UserSend
import com.fueltrac.utilities.InitializeProgressDialog
import com.fueltrac.utilities.showProgressDialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    private lateinit var context: Context
    var user = UserSend()
    private lateinit var repository : Repository
    private var _loginResponse = MutableLiveData<UserResponse>()
    var loginResponse :LiveData<UserResponse> = _loginResponse

    private var loginCompostie = CompositeDisposable()


    fun setActivity(context: Context) {
        this.context = context
        repository = Repository(context)
    }


    fun checkUser(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            InitializeProgressDialog(context)
        }
        loginCompostie.add(repository.login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(Consumer {

                showProgressDialog(true)
//                Toast.makeText(context,"start Loading ",Toast.LENGTH_LONG)
            })
            .subscribe(
                {
                    showProgressDialog(false)
                    if (it.getStatus() == true)
                        it.getData()?.let { it1 -> run {
                            repository.insertUser(it1)
                        }
                        _loginResponse.value = it.getData()
                    } else {
                        Toast.makeText(context, it.getMessage(), Toast.LENGTH_LONG).show()
                    }
                },
                {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    showProgressDialog(false )
                }
            ))

    }

}
