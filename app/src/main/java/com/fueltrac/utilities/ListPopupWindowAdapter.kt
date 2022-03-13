package com.fueltrac.utilities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.fueltrac.R
import com.fueltrac.model.entities.pumps


class ListPopupWindowAdapter(context: Context, itemList: List<pumps>) :
    BaseAdapter() {
    var mLayoutInflater: LayoutInflater
    var mItemList: List<pumps>
    override fun getCount(): Int {
        return mItemList.size
    }

    override fun getItem(i: Int): pumps {
        return mItemList[i]
    }

    override fun getItemId(i: Int): Long {
        return mItemList[i].id as Long
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.menu_item, null)
            holder = ViewHolder(convertView)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.tvTitle.setText(getItem(position).pumpName)
        return convertView
    }

    internal class ViewHolder(view: View) {
        var tvTitle: TextView

        init {
            tvTitle = view.findViewById(R.id.text)
        }
    }

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mItemList = itemList
    }
}
