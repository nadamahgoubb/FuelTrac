package com.fueltrac.view.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.fueltrac.R
import com.fueltrac.databinding.ActivityLoginBinding
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.utilities.*
import com.fueltrac.view.Dialogs.CustomDialogClass
import com.fueltrac.view.activities.home.HomePage
import com.fueltrac.view.activities.supervisor.SupervisorActivity


class LoginActivity : AppCompatActivity() {


    private lateinit var mViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mViewModel.setActivity(this@LoginActivity)

        binding.vm = mViewModel
        binding.lifecycleOwner = this@LoginActivity
//        CustomDialogClass(this).FuelingDialog()
        //   goSupervisorHome(this@LoginActivity)


        mViewModel.loginResponse.observe(this, { loginresult(it) })

    }

    private fun loginresult(userResponse: UserResponse) {

        user_Token = userResponse!!.token.toString()
        user_id = userResponse!!.userId!!
        user_Station_Id = userResponse.getStations()?.get(0)?.id!!
        user_type = userResponse.userType?.let { user_type.getUserType(it) }!!

        if (userResponse.userType != null &&
            userResponse.userType!!.equals(UserType.User.toString())
        ) {
            goSearch(this@LoginActivity)

        } else {
            goSupervisorHome(this@LoginActivity)

        }
    }

}