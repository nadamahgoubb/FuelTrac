package com.fueltrac.view.fragments.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fueltrac.R
import com.fueltrac.databinding.TransactionFragmentBinding
import com.fueltrac.model.entities.TransactionModel
import java.util.*

class TransactionFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionFragment()
    }


    lateinit var transactions  :ArrayList<TransactionModel>
    private lateinit var transactionAdapter: TransactionAdopter
    lateinit var binding: TransactionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.transaction_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this@TransactionFragment
        transactions = ArrayList<TransactionModel>()
        transactionAdapter= activity?.let { TransactionAdopter(it, transactions) }!!

        binding.listItem.adapter = transactionAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        activity?.let { viewModel.setActivity(it) }
        activity?.let { transactionAdapter.onFonished.observe(it ,{
            viewModel.FinishTransaction(it)
        })}
        activity?.let {
            viewModel.transactionsResponse.observe(it, {
                if (it.isNotEmpty()){
                    binding.emptyLyout.visibility = View.GONE
                    transactions.clear()
                    transactions.addAll(it)
                    transactionAdapter.notifyDataSetChanged()
                }
                else
                    binding.emptyLyout.visibility = View.VISIBLE



            })
        }

    }

}