package com.fueltrac.view.activities.supervisor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fueltrac.R
import com.fueltrac.databinding.FillterResultItemBinding
import com.fueltrac.databinding.FilterResultItemBinding
import com.fueltrac.model.entities.Value


class HFRecyclerView (private val withHeader: Boolean =true , private val withFooter: Boolean =false)
    : RecyclerView.Adapter<HFRecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
        private const val TYPE_FOOTER = 2
    }

    var data: List<Value> = ArrayList<Value>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //region Get View
     fun getItemView(inflater: LayoutInflater, parent: ViewGroup): ViewHolder{
        return  ViewHolder(DataBindingUtil.inflate(
            inflater,
            R.layout.fillter_result_item,
            parent,
            false
        ))
     }

    fun getHeaderView(inflater: LayoutInflater, parent: ViewGroup):  ViewHolder?{
        return  ViewHolder(DataBindingUtil.inflate(
            inflater,
            R.layout.fillter_result_item,
            parent,
            false
        ))
    }

    fun getFooterView(inflater: LayoutInflater, parent: ViewGroup): ViewHolder?{
        return  ViewHolder(DataBindingUtil.inflate(
            inflater,
            R.layout.fillter_result_item,
            parent,
            false
        ))
    }
    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_ITEM -> getItemView(inflater, parent)
            TYPE_HEADER -> getHeaderView(inflater, parent) ?: throw RuntimeException("Empty view")
            TYPE_FOOTER -> getFooterView(inflater, parent) ?: throw RuntimeException("Empty view")
            else -> throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
        }
    }

    override fun getItemCount(): Int {
        var itemCount = getRealItemCount()
        if (withHeader) itemCount++
        if (withFooter) itemCount++
        return itemCount
    }

    override fun getItemViewType(position: Int): Int =
        if (withHeader && isPositionHeader(position)) TYPE_HEADER
        else if (withFooter && isPositionFooter(position)) TYPE_FOOTER
        else TYPE_ITEM

    private fun isPositionHeader(position: Int): Boolean = position == 0

    private fun isPositionFooter(position: Int): Boolean = position == itemCount - 1

    protected fun getRealItemCount(): Int = data.size

    protected fun getItem(position: Int): Value =
        if (withHeader) data[position - 1] else data[position]

    inner class ViewHolder(binding: FillterResultItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var binding: FillterResultItemBinding


        init {
            this.binding = binding
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (withHeader&&!isPositionHeader(position))
            holder.binding.filterresult=data[position-1]
    }

}