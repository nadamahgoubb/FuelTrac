package com.fueltrac.view.activities.supervisor

import android.app.Activity
import android.icu.text.StringSearch
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.model.Repository
import com.fueltrac.model.entities.*
import com.fueltrac.utilities.InitializeProgressDialog
import com.fueltrac.utilities.showProgressDialog
import com.fueltrac.utilities.user_Token
import com.fueltrac.utilities.user_id
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.tan

class SupervisorViewModel :ViewModel() {

    lateinit var activity: Activity

    private var _tankInfo = MutableLiveData<TankInfo>()
     var tankInfo :LiveData<TankInfo> =_tankInfo
    lateinit var stations : LiveData<List<Stations>>
    lateinit var tanks : LiveData<List<tanks>>
    lateinit var repository: Repository


    var fillter = CompositeDisposable()

    fun init (activity: Activity) {
        this.activity = activity;
        repository = Repository(activity)
        stations = repository.getStationById(user_id.toString())!!
        tanks = repository.getTanksById(1)!!

    }

    fun getTankes(item: Stations) {
        tanks = item.id?.let { repository.getTanksById(it) }!!
    }
    lateinit var selectedtank :tanks
    var isSelected = false
    fun getTankInfo(tank : tanks) {
        selectedtank = tank
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            InitializeProgressDialog(activity)
        }

        repository.getTankInfo(tank.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressDialog(true)
            }.subscribe({
                showProgressDialog(false)
                isSelected = true
                if (it.getStatus() == true)
                    _tankInfo.value =it.getData()
                else
                    Toast.makeText(activity,it.getMessage(),Toast.LENGTH_LONG).show()
            },{
                showProgressDialog(false)
                Toast.makeText(activity,it.message,Toast.LENGTH_LONG).show()

            })
    }

    var _filterDataResponse = MutableLiveData< List<Value>>()
    var fillterData : LiveData< List<Value>> = _filterDataResponse

    fun getdate(from: Calendar, to: Calendar ,tankid :String) {
        val myFormat = "yyyy-MM-dd'T'HH:mm:ss'.000Z'" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        var fromstr = sdf.format(from.time)
        var tostr = sdf.format(to.time)



        fillter.add(repository.filterData(FilterDataRequest(tankid,fromstr, tostr, user_Token))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressDialog(true)
            }.subscribe({
                showProgressDialog(false)
                if (it.getStatus() == true)
                   _filterDataResponse.value = it.getData()?.mappedListTankMeasures?.value
                else
                    Toast.makeText(activity,it.getMessage(),Toast.LENGTH_LONG).show()
            },{
                showProgressDialog(false )
                Toast.makeText(activity,it.message,Toast.LENGTH_LONG).show()

            }
            ))
    }

}