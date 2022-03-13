package com.fueltrac.utilities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.ListPopupWindow
import com.fueltrac.R
import com.fueltrac.model.entities.pumps

var  Shared_user_Token  : String = "userToken"
var  Shared_user_Email  : String = "user_Email"
var  Shared_user_name  : String = "user_name"
var  Shared_user_Type  : String = "user_Type"
var  Shared_user_Id  : String = "user_Id"
var  app_Preference  : String = "fuel_Preference"
var  app_Version  : String = "app_Version"
var  app_lang  : String = "app_lang"

var  user_Token  : String = ""
var  user_id  : Int = -1
var  user_Station_Id  : Int = -1
var user_type : UserType =UserType.User


enum class UserType(val type: String) {
    User("User"),
    Supervisor("Supervisor"),
    MrinaUser("MrinaUser");

    override fun toString(): String {
        return type
    }
    public fun getUserType ( type  :String = "User" ) : UserType?
    {
        if (type  .equals("User")) return User
        if (type  .equals("Supervisor")) return Supervisor
        if (type  .equals("MrinaUser")) return MrinaUser
        else return null
    }

}

enum class ClientType(val type: Int) {
    Customer(1),
    Service(2),
    Guest(3)
}

var progressDialog: Dialog? = null

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun InitializeProgressDialog(context: Context?) {
    val alertDialogBuilder = AlertDialog.Builder(context).setCancelable(false)
    alertDialogBuilder.setView(R.layout.progress_dialog_loader)
    progressDialog = alertDialogBuilder.create()
    (progressDialog as AlertDialog?)?.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    (progressDialog as AlertDialog?)?.window?.setBackgroundDrawableResource(R.color.dialogTrans)
}
fun showProgressDialog(show: Boolean) {
    if (progressDialog != null) {
        if (show) progressDialog!!.show() else progressDialog!!.hide()
    }
}


// region Popup dropdown list
fun showMenu(
    context: Context?,
    v: View?,
    itemList: List<pumps>,
    dropDown: DropDown
) {
    val popupWindow = ListPopupWindow(
        context!!
    )
    val adapter: ListAdapter = ListPopupWindowAdapter(context, itemList)
    popupWindow.anchorView = v
    popupWindow.setAdapter(adapter)
    popupWindow.setOnItemClickListener { adapterView, view, i, l ->
        val item: pumps = adapterView.getItemAtPosition(i) as pumps
        dropDown.onItemClick(item)
        popupWindow.dismiss()
    }
    popupWindow.show()
}

interface DropDown {
    fun onItemClick(item: pumps?)
}