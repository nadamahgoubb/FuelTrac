package com.fueltrac.view.activities.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fueltrac.R
import com.fueltrac.utilities.*


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (user_id == -1)
           goToLogin(this@Splash);
        else if (user_type==UserType.User)
            goSearch(this@Splash);
        else if (user_type==UserType.Supervisor)
            goSupervisorHome(this@Splash);
    }
}
