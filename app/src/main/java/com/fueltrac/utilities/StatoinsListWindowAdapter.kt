package com.fueltrac.utilities
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.fueltrac.R
import com.fueltrac.databinding.MenuItemBinding
import com.fueltrac.model.entities.Stations
import com.fueltrac.model.entities.pumps


class StatoinsListWindowAdapter(var context: Context, var itemList: List<Stations>) :
    BaseAdapter() {


    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(i: Int): Stations {
        return itemList[i]
    }

    override fun getItemId(i: Int): Long {
        return itemList[i].id?.toLong()!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return if (convertView != null) {
            convertView
        } else {
            val view = layoutInflater.inflate(R.layout.menu_item, null)
            val binding = MenuItemBinding.bind(view)
            val item = itemList[position]
            binding.text.text = item.stationName
            view
        }
    }

}
