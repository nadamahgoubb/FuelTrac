package com.fueltrac.view.fragments.transactions

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.fueltrac.R
import com.fueltrac.databinding.AdapterTransactionBinding
import com.fueltrac.model.entities.TransactionModel
import com.fueltrac.view.Dialogs.CustomDialogClass
import java.util.ArrayList

class TransactionAdopter(var context: Context, var data: ArrayList<TransactionModel>): RecyclerView.Adapter<TransactionAdopter.ViewHolder>(),
    CustomDialogClass.onActionHappen {


    lateinit var selected :TransactionModel
    var onFonished = MutableLiveData<TransactionModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : AdapterTransactionBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.adapter_transaction,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.transactionCard.setOnClickListener {
            //todo get quantaty and call api
            selected = data[position]
            CustomDialogClass(context).FuelingFinished(this)
        }

        holder.binding.transaction=data[position]


    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(binding: AdapterTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: AdapterTransactionBinding

        init {
            this.binding = binding
        }
    }

    override fun onEnter(num: String, dialog: Dialog) {
        dialog.dismiss()
        selected.quantity = num
        onFonished.value = selected
    }
}
