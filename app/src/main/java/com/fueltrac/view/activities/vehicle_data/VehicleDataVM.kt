package com.fueltrac.view.activities.vehicle_data

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.model.Repository
import com.fueltrac.model.entities.Searchresult
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.utilities.InitializeProgressDialog
import com.fueltrac.utilities.showProgressDialog
import com.fueltrac.utilities.user_Station_Id
import com.fueltrac.view.Dialogs.CustomDialogClass
import com.fueltrac.view.activities.home.HomePage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class VehicleDataVM : ViewModel() {

    lateinit var activity : Activity
     lateinit var transactionModel: TransactionModel
     lateinit var searchresult : Searchresult
     lateinit var repository: Repository

    var _checkNumber = MutableLiveData<Boolean>()
    var checkNumber : LiveData<Boolean> = _checkNumber

     var check = CompositeDisposable()

     fun setactivity(activity: Activity) {
         this.activity= activity
         repository= Repository(activity)
     }
    fun backSearch(){
        var intent = Intent(activity, HomePage::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun startfuling(DriverNum : String) {
        val sdf = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss")
        transactionModel.StartDateTime = sdf.format(Date())
        transactionModel.PlateNumber =searchresult.getPlateNumber()
        transactionModel.StationId = user_Station_Id.toString()
        transactionModel.DriveNumber = DriverNum
        transactionModel.department = searchresult.getDepartment()
        transactionModel.type = searchresult.getType()


        repository.insertTransactionData(transactionModel)

        CustomDialogClass(activity).FuelingStarted()
    }

    fun CheckDriver(number : String ){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            InitializeProgressDialog(activity)
        }

        check.add(repository.CheckDriver(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressDialog(true)
            }
            .subscribe({
                showProgressDialog(false)
                if (it.getStatus() == true)
                    _checkNumber.value=it.getStatus()
                else
                    Toast.makeText(activity,it.getMessage(),Toast.LENGTH_LONG).show()
            }, {
                showProgressDialog(false)
                Toast.makeText(activity,it.message,Toast.LENGTH_LONG).show()

            })
        )

    }

}