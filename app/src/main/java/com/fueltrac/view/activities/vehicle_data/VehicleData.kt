package com.fueltrac.view.activities.vehicle_data

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fueltrac.R
import com.fueltrac.databinding.ActivityVehicleDataBinding
import com.fueltrac.model.entities.Searchresult
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.view.Dialogs.CustomDialogClass
import com.google.gson.Gson

class VehicleData : AppCompatActivity(), CustomDialogClass.onActionHappen {
    lateinit var binding : ActivityVehicleDataBinding

    lateinit var vihicledataVM :VehicleDataVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init view model
        vihicledataVM = ViewModelProvider(this).get(VehicleDataVM::class.java)
        vihicledataVM.setactivity(this)

        // get data from intent
        if (intent.hasExtra("transaction"))
            vihicledataVM.transactionModel =Gson().fromJson(intent.extras?.getString("transaction"),TransactionModel::class.java)
        if (intent.hasExtra("data"))
            vihicledataVM.searchresult = Gson().fromJson(intent.extras?.getString("data"),Searchresult::class.java )

        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_data)
        binding.lifecycleOwner = this@VehicleData
        //set var
        binding.vm=vihicledataVM
        binding.cardata = vihicledataVM.searchresult


        binding.startfuling.setOnClickListener {CustomDialogClass(this).DriverDialog(this) }


    }

    override fun onEnter(num: String, dialog: Dialog) {
        vihicledataVM.CheckDriver(num)

        vihicledataVM.checkNumber.observe(this,{
            vihicledataVM.startfuling(num)
            dialog.dismiss()
        })

    }
}