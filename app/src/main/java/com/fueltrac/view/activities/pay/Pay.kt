package com.fueltrac.view.activities.pay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.fueltrac.R
import com.fueltrac.databinding.ActivityPayBinding
import com.fueltrac.view.activities.login.LoginViewModel

class Pay : AppCompatActivity() {

    private lateinit var mViewModel : PayViewModel

    private lateinit var binding : ActivityPayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pay)
        mViewModel = ViewModelProvider(this).get(PayViewModel::class.java)
        mViewModel.setActivity(this@Pay)
        binding.lifecycleOwner = this@Pay
        binding.mvm=mViewModel


        binding.CashCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.cash = isChecked
            binding.KnetCheckbox.isChecked=!isChecked
            mViewModel.cash=isChecked
        }
        binding.KnetCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.cash = !isChecked
            binding.CashCheckbox.isChecked=!isChecked
            mViewModel.cash=!isChecked
        }
    }
}