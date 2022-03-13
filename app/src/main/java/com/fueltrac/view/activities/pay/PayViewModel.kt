package com.fueltrac.view.activities.pay

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fueltrac.model.Repository

class PayViewModel : ViewModel() {

    private lateinit var context: Context
    private lateinit var repository : Repository
    var cash : Boolean = false
    lateinit var transactionNum : String



    fun setActivity(context: Context) {
        this.context = context
        repository = Repository(context)
    }



}
