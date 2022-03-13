package com.fueltrac.view.fragments.search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.model.Repository
import com.fueltrac.model.entities.SearchSend
import com.fueltrac.model.entities.Searchresult
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.model.entities.pumps
import com.fueltrac.utilities.*
import com.fueltrac.view.Dialogs.CustomDialogClass
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : ViewModel() {
    private lateinit var context: Context
    private lateinit var repository : Repository

    var _SearchResponse = MutableLiveData<Searchresult>()
    public var searchResponse :LiveData<Searchresult> = _SearchResponse

    var _showStatusbar = MutableLiveData<Boolean>(false)
    public var showStatusbar :LiveData<Boolean> = _showStatusbar

    lateinit var PumpsResponse :LiveData<List<pumps>>
    var searchsend = SearchSend()
    var transactionModel  = TransactionModel()



    private var SearchCompostie = CompositeDisposable()


    fun setActivity(context: Context) {
        this.context = context
        repository = Repository(context)
        PumpsResponse= repository.GetPumps(user_Station_Id)!!

    }


    fun rfidchangestatus( view : View ) {



        if(showStatusbar.value == false)
            enableRfid()
        else
            disableRfid()
    }

    fun SearchVehicles(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            InitializeProgressDialog(context)
        }
        SearchCompostie.add(
            repository.SearchVehicles(searchsend)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe (Consumer {
                    showProgressDialog(true)
                    //                Toast.makeText(context,"start Loading ",Toast.LENGTH_LONG)
                })
                .subscribe(
                    {
                        showProgressDialog(false)
                        if (it?.getStatus() == true)
                            _SearchResponse.value= it.getData()
                         else{
                            CustomDialogClass(context).showRFIDDialog(false )
//                            Toast.makeText(context, it?.getMessage(), Toast.LENGTH_LONG)
                        }
                    },
                    {
                        Toast.makeText(context,it.message, Toast.LENGTH_LONG)
                        showProgressDialog(false)

                    }
                ))


    }


    fun enableRfid() {
        _showStatusbar.value= true
//        binding.rfidstatus.visibility = View.VISIBLE
        val intent = Intent("com.handheldgroup.anysend.SET_STATE")
        intent.putExtra("device", true)
        context.sendBroadcast(intent)
        Log.d("rashad", "enableRfid: SET_STATE true ")
    }

    fun disableRfid() {
        _showStatusbar.value= false
        if (searchsend.plateNumber.equals(""))
            CustomDialogClass(context).showRFIDDialog(true)
//        binding.rfidstatus.visibility = View.GONE
        val intent = Intent("com.handheldgroup.anysend.SET_STATE")
        intent.putExtra("device", false)
        context.sendBroadcast(intent)
        Log.d("rashad", "disableRfid: SET_STATE false ")
    }
}
