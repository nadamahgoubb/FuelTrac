package com.fueltrac.view.activities.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.fueltrac.R
import com.fueltrac.databinding.ActivitySearchBinding

class HomePage : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.lifecycleOwner = this@HomePage



        var navController= Navigation.findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(binding.navView, navController)
        if (intent.hasExtra("displayTransactions"))
        {
            navController.graph.startDestination = R.id.navigation_transaction
            navController.navigate(R.id.navigation_transaction)
        }
        else
            navController.graph.startDestination = R.id.navigation_search


    }

//
//    private val TRIGGER = KeyEvent.KEYCODE_F2
//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (event.keyCode == TRIGGER) {
//
//
//            Log.d("rashad", "onKeyDown: start scaning ")
//            enableRfid()
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
//        if (event.keyCode == TRIGGER) {
//
//            Log.d("rashad", "onKeyUp: end scaning ")
//            disableRfid()
//            return true
//        }
//        return super.onKeyUp(keyCode, event)
//    }


}