package com.fueltrac.view.Dialogs

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import com.fueltrac.R
import com.fueltrac.databinding.*
import com.fueltrac.view.activities.home.HomePage
import java.text.SimpleDateFormat
import java.util.*


class CustomDialogClass(var context: Context)  {


    var dialog = Dialog(context)


    /**
     * @param rfid change the dialog type
     * true for how to use Rfid
     * false for tag or plate number not found in system
     */
    fun showRFIDDialog(rfid: Boolean) {

        val binding: RfidDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.rfid_dialog,
            null,
            false
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        binding.rfidm = rfid
        binding.btnYes.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun FuelingFinished(DriverNumberInter : onActionHappen ) {


        val binding: FuelingFinishedDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fueling_finished_dialog,
            null,
            false
        )


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.btnDone.setOnClickListener {
            if (binding.fuelEd.text.toString() != "")
            {
                DriverNumberInter.onEnter(binding.fuelEd.text.toString(),dialog )
            }else
                binding.fuelEd.error=context.resources.getString(R.string.error )
        }
        dialog.show()

    }

    fun FuelingFinishedDesc() {

        val binding: FuelingFinishedDescDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fueling_finished_desc_dialog,
            null,
            false
        )

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        binding.btnDone.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun FuelingStarted() {

        val binding: FuelingStartedDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fueling_started_dialog,
            null,
            false
        )
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        binding.btnViewTransaction.setOnClickListener{

            var intent =Intent(context, HomePage::class.java)
            intent.putExtra("displayTransactions", true)
            context.startActivity(intent)

            (context as Activity ).finish()

            dialog.dismiss()
        }
        binding.btnBackToSearch.setOnClickListener{
            context.startActivity(Intent(context, HomePage::class.java))
            (context as Activity ).finish()
            dialog.dismiss()
        }
        dialog.show()

    }

    fun DriverDialog(DriverNumberInter : onActionHappen ){


        var  Num = ""
        val binding: DriverNumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.driver_num,
            null,
            false
        )
        binding.num=Num
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        binding.btnViewTransaction.setOnClickListener{
            DriverNumberInter.onEnter(binding.drivernumber.text.toString(), dialog)

        }

        dialog.show()

    }

    interface onActionHappen{
        fun onEnter(num: String, dialog: Dialog)
    }




    fun FilterDialog(res :onfilltertimesEntered) {

        var fromCal = Calendar.getInstance()
        var toCal = Calendar.getInstance()

        var from_validation = false
        var to_validation = false
        val binding: FilterDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.filter_dialog,
            null,
            false
        )

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.fromcalender.setOnClickListener {

                DatePickerDialog(context, { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                    run {

                        from_validation= true
                        fromCal.set(Calendar.YEAR, year)
                        fromCal.set(Calendar.MONTH, month)
                        fromCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        val myFormat = "dd.MM.yyyy" // mention the format you need
                        val sdf = SimpleDateFormat(myFormat, Locale.US)
                        binding.fromcalender.setText (sdf.format(fromCal.time))

                    }

                },  fromCal.get(Calendar.YEAR),
                    fromCal.get(Calendar.MONTH),
                    fromCal.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.tocalender.setOnClickListener {


            DatePickerDialog(context, { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                run {

                    to_validation=true
                    toCal.set(Calendar.YEAR, year)
                    toCal.set(Calendar.MONTH, month)
                    toCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd.MM.yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    binding.tocalender.setText (sdf.format(toCal.time))
                }

            },  toCal.get(Calendar.YEAR),
                toCal.get(Calendar.MONTH),
                toCal.get(Calendar.DAY_OF_MONTH)).show()

        }
        binding.btnShowResults.setOnClickListener {
            if (!from_validation)
                binding.fromcalender.error = context.resources.getString(R.string.requredfelid)
            if (!to_validation)
                binding.tocalender.error = context.resources.getString(R.string.requredfelid)
            if (from_validation&&to_validation)
            {
                res.onEnterFromAndTODate(fromCal,toCal)
                dialog.dismiss()

            }
        }
        binding.Cancel.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()

    }



    fun SpecidiOilDialog() {


        val binding: SpecifiOilDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.specifi_oil_dialog,
            null,
            false
        )


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.NextFuelingBtn.setOnClickListener {
            dialog.dismiss()
        }
        binding.SkipAndStartBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun FuelingDialog() {


        val binding: FuelingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fueling_dialog,
            null,
            false
        )


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.btnFinishFuueling.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }



    interface onfilltertimesEntered{
        fun onEnterFromAndTODate(from :Calendar, to :Calendar );
    }


}