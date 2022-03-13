package com.fueltrac.utilities

import android.app.Activity
import android.content.Intent
import android.os.Handler
import com.fueltrac.view.activities.MrinaTest
import com.fueltrac.view.activities.login.LoginActivity
import com.fueltrac.view.activities.home.HomePage
import com.fueltrac.view.activities.login.Splash
import com.fueltrac.view.activities.supervisor.FilterActivity
import com.fueltrac.view.activities.supervisor.SupervisorActivity
import com.fueltrac.view.activities.vehicle_data.VehicleData
import java.util.*

fun goToLogin(activity :Activity) {
    Handler().postDelayed({
        val mIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(mIntent)
        activity.finish()
    }, 2000)
}


fun goLogin(activity :Activity) {

        val mIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(mIntent)
        activity.finish()

}
fun goMarinaTest(activity :Activity) {

    val mIntent = Intent(activity, MrinaTest::class.java)
    activity.startActivity(mIntent)
    activity.finish()

}

fun goSplash(activity :Activity) {

        val mIntent = Intent(activity, Splash::class.java)
        activity.startActivity(mIntent)
        activity.finish()

}
fun goSearch(activity :Activity) {
        val mIntent = Intent(activity, HomePage::class.java)
        activity.startActivity(mIntent)
        activity.finish()
}
fun goSupervisorHome(activity :Activity) {
        val mIntent = Intent(activity, SupervisorActivity::class.java)
        activity.startActivity(mIntent)
        activity.finish()
}
fun goFilterActivity(activity: Activity, to: Calendar, from: Calendar,tankid :String) {
        val mIntent = Intent(activity, FilterActivity::class.java)
    mIntent.putExtra("tankid" ,tankid)
    mIntent.putExtra("from" ,from)
    mIntent.putExtra("to" ,from)
        activity.startActivity(mIntent)
        activity.finish()
}
fun goSearchResult(activity :Activity) {
        val mIntent = Intent(activity, VehicleData::class.java)
        activity.startActivity(mIntent)
        activity.finish()
}

