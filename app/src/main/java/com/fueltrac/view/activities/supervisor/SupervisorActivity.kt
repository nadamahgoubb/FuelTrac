package com.fueltrac.view.activities.supervisor

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.fueltrac.R
import com.fueltrac.databinding.ActivitySupervisorBinding
import com.fueltrac.model.entities.Stations
import com.fueltrac.model.entities.tanks
import com.fueltrac.utilities.*
import com.fueltrac.view.Dialogs.CustomDialogClass
import com.skydoves.progressview.progressView
import java.util.*

class SupervisorActivity : AppCompatActivity(), CustomDialogClass.onfilltertimesEntered {

    lateinit var supervisorViewModel: SupervisorViewModel
    lateinit var binding: ActivitySupervisorBinding
    lateinit var stationspopupWindow: ListPopupWindow
    lateinit var tankspopupWindow: ListPopupWindow
    lateinit var adapter: StatoinsListWindowAdapter
    lateinit var adapter1: TanksListWindowAdapter
    lateinit var activity: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = this@SupervisorActivity
        binding = setContentView(this, R.layout.activity_supervisor)
        supervisorViewModel = ViewModelProvider(this).get(SupervisorViewModel::class.java)
        supervisorViewModel.init(this@SupervisorActivity)
        binding.vm = supervisorViewModel
        binding.lifecycleOwner = this@SupervisorActivity


        binding.progressView1.highlightView.colorGradientEnd =
            ContextCompat.getColor(this, R.color.red)
        binding.progressView1.highlightView.colorGradientStart =
            ContextCompat.getColor(this, R.color.orange)


        supervisorViewModel.stations.observe(this, {
            stationspopupWindow = ListPopupWindow(activity)
            adapter = StatoinsListWindowAdapter(activity, it)
            stationspopupWindow.anchorView = binding.stationCard
            stationspopupWindow.setAdapter(adapter)
            stationspopupWindow.setOnItemClickListener { adapterView, view, i, l ->
                val item: Stations = adapterView.getItemAtPosition(i) as Stations
                supervisorViewModel.getTankes(item)
                binding.stations.setText(item.stationName)
                stationspopupWindow.dismiss()
            }


        })

        binding.stations.setOnClickListener(View.OnClickListener { stationspopupWindow.show() })


        supervisorViewModel.tanks.observe(this, {
            tankspopupWindow = ListPopupWindow(activity)
            adapter1 = TanksListWindowAdapter(activity, it)
            tankspopupWindow.anchorView = binding.tanks
            tankspopupWindow.setAdapter(adapter1)
            tankspopupWindow.setOnItemClickListener { adapterView, view, i, l ->
                val item: tanks = adapterView.getItemAtPosition(i) as tanks
                binding.tanks.setText(item.tankName)
                supervisorViewModel.getTankInfo(item)
                tankspopupWindow.dismiss()
            }
        })
        binding.tanks.setOnClickListener { tankspopupWindow?.let { it.show() } }

        supervisorViewModel.tankInfo.observe(this, {
            binding.noData.visibility = View.GONE
            binding.container.visibility = View.VISIBLE
            //    binding.

            binding.tank = it
        })

        binding.fillter.setOnClickListener {
            if (supervisorViewModel.isSelected)
                CustomDialogClass(activity).FilterDialog(this)
            else
                binding.tanks.error = activity.resources.getString(R.string.requredfelid)

        }


    }

    override fun onEnterFromAndTODate(from: Calendar, to: Calendar) {

        goFilterActivity(
            this@SupervisorActivity, to, from,
            supervisorViewModel.selectedtank.id.toString()
        )
    }

}