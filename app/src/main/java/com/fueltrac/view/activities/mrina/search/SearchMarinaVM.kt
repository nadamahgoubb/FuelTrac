package com.fueltrac.view.activities.mrina.search

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.view.Dialogs.CustomDialogClass

class SearchMarinaVM : ViewModel() {

    lateinit var activity :Activity
    var _showStatusbar = MutableLiveData<Boolean>(false)
    public var showStatusbar : LiveData<Boolean> = _showStatusbar

    fun init(activity: Activity){
        this.activity= activity
    }


    fun rfidchangestatus( view : View) {

        if(showStatusbar.value == false)
            enableRfid()
        else
            disableRfid()
    }



    fun enableRfid() {
        _showStatusbar.value= true
//        binding.rfidstatus.visibility = View.VISIBLE
        val intent = Intent("com.handheldgroup.anysend.SET_STATE")
        intent.putExtra("device", true)
        activity.sendBroadcast(intent)
        Log.d("rashad", "enableRfid: SET_STATE true ")
    }

    fun disableRfid() {
        _showStatusbar.value= false
//        if (searchsend.plateNumber.equals(""))
//            CustomDialogClass(activity).showRFIDDialog(true)
//        binding.rfidstatus.visibility = View.GONE
        val intent = Intent("com.handheldgroup.anysend.SET_STATE")
        intent.putExtra("device", false)
        activity.sendBroadcast(intent)
        Log.d("rashad", "disableRfid: SET_STATE false ")
    }

}