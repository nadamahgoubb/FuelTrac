package com.fueltrac.view.reciever
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fueltrac.model.entities.Searchresult

class RfidDataReciever() : BroadcastReceiver() {

    var _tagData  = MutableLiveData<String>()
    var tagData : LiveData<String> = _tagData

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "com.handheldgroup.anysend.RESULT") return

        // The device which sent this data. Scanner = 1, RFID = 2
        val device = intent.getIntExtra("device", -1)

        // Numeric representation of the tag type or symboligy
        val type = intent.getIntExtra("type", -1)

        // String representation of the tag type or symboligy
        val typeName = intent.getStringExtra("type_name")

        // Data as byte array
        val dataArray = intent.getByteArrayExtra("data")

        // Data as String
        val dataString = intent.getStringExtra("string")
        Log.d(
            "rashad",
            "Device #$device returned $dataString for type $typeName"
        )
        _tagData.value=dataString
//        viewmodel.searchData.binding.plateNumber.text ="Device #$device returned $dataString for type $typeName" as Editable
    }


}
