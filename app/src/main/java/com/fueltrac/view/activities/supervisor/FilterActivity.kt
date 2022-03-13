package com.fueltrac.view.activities.supervisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.fueltrac.R
import com.fueltrac.databinding.ActivityFilterBinding
import com.fueltrac.databinding.ActivityFilterResultBinding
import com.fueltrac.model.entities.FilterModel
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.model.entities.Value
import java.util.*

class FilterActivity : AppCompatActivity() {

    lateinit var binding : ActivityFilterResultBinding
    lateinit var viewModel: SupervisorViewModel


    var Results = ArrayList<Value>()
    lateinit var filterresultadapter: HFRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_filter_result)
        binding.lifecycleOwner = this@FilterActivity

        var tankid : String = intent.extras?.get("tankid") as String
        var from : Calendar = intent.extras?.get("from") as Calendar
        var to : Calendar = intent.extras?.get("to") as Calendar

        viewModel = ViewModelProvider(this).get(SupervisorViewModel::class.java)
        viewModel.init(this)
        viewModel.getdate(from,to,tankid)


        filterresultadapter= HFRecyclerView(withHeader = true, withFooter = false )

        binding.recyclerView.adapter = filterresultadapter

        viewModel.fillterData.observe(this,{
            filterresultadapter.data=it
        })





    }
}