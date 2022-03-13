package com.fueltrac.view. fragments.transactions

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fueltrac.R
import com.fueltrac.model.Repository
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.utilities.InitializeProgressDialog
import com.fueltrac.utilities.showProgressDialog
import com.fueltrac.utilities.user_Token
import com.fueltrac.utilities.user_id
import com.fueltrac.view.Dialogs.CustomDialogClass
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class TransactionViewModel :ViewModel() {

    private lateinit var context: Context
    private lateinit var repository : Repository

    var disposable = CompositeDisposable()

    lateinit var transactionsResponse : LiveData<List<TransactionModel>>

    fun setActivity(context: Context) {
        this.context = context
        repository = Repository(context)
        transactionsResponse= repository.GetTransactions(user_id)!!

    }

    fun FinishTransaction( transactionModel: TransactionModel?) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            InitializeProgressDialog(context)
        }
        transactionModel?.token = user_Token
        disposable.add(repository.FinishTransaction(transactionModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgressDialog(true)

            }.subscribe({
                showProgressDialog(false )
                if (it.getStatus() == true)
                {
                    transactionModel?.let { it1 -> repository.removeTransactionData(it1) }
                    Toast.makeText(context,context.resources.getString(R.string.transaction_finished_successfuly),Toast.LENGTH_LONG).show()


                }else
                    Toast.makeText(context,it.getMessage(),Toast.LENGTH_LONG).show()

            }
            , {
                    showProgressDialog(false )
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }))

    }
}