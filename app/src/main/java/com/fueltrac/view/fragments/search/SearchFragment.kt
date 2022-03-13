package com.fueltrac.view.fragments.search

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.fueltrac.R
import com.fueltrac.databinding.SearchFragmentBinding
import com.fueltrac.model.entities.Searchresult
import com.fueltrac.model.entities.pumps
import com.fueltrac.view.activities.vehicle_data.VehicleData
import com.fueltrac.view.reciever.RfidDataReciever
import com.google.gson.Gson

class SearchFragment : Fragment() {


    lateinit var searchVM:SearchViewModel
    lateinit var binding:SearchFragmentBinding

    companion object {
        fun newInstance() = SearchFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding  = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )
        //init VM
        searchVM= activity?.let { ViewModelProvider(it).get(SearchViewModel::class.java) }!!
        activity?.let { searchVM.setActivity(it) }


        binding.svm = searchVM
        binding.lifecycleOwner = this@SearchFragment




        // init the pump spinner
        activity?.let { searchVM.PumpsResponse.observe(it,{ data  -> spinnerdata(data)}) }
        activity?.let { searchVM.searchResponse.observe(it,{ data  -> onSerchResulut(data)}) }


        // Register the RFID receiver
        var reciever =RfidDataReciever()
        context?.registerReceiver(reciever, IntentFilter("com.handheldgroup.anysend.RESULT"))

        activity?.let { reciever.tagData.observe(it,{ data  -> onRfidResulut(data)}) }

        return binding.root

    }

    private fun onRfidResulut(tag: String) {
        binding.plateNumber.setText(tag)
    }

    private fun spinnerdata( it: List<pumps>) {
        val pumpAdopter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, it)
        pumpAdopter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bumpNumber.adapter = pumpAdopter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.searchCard.setOnClickListener{startSearsh()}
        activity?.let { searchVM.setActivity(it) }
    }

    private fun onSerchResulut(data: Searchresult) {

        var intent = Intent(activity, VehicleData::class.java)
        intent.putExtra("data",Gson().toJson(data))
        intent.putExtra("transaction",Gson().toJson(searchVM.transactionModel))
        startActivity(intent)
        activity?.finish()
        Toast.makeText(activity,data.toString(),Toast.LENGTH_LONG)
    }
    fun startSearsh() {
        Log.d("rashad", "start: search clicked")
        searchVM.transactionModel.PlateNumber = searchVM.searchsend.plateNumber

        if (binding.bumpNumber.selectedItem != null&&!searchVM.searchsend.plateNumber.equals("")){
            searchVM.transactionModel.pumpNumber = (binding.bumpNumber.selectedItem as pumps).id!!.toString()
            searchVM.SearchVehicles()
        }



    }

    override fun onResume() {
        super.onResume()
        activity?.let { searchVM.setActivity(it) }
    }
}