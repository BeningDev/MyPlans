package com.bening.myplans.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bening.myplans.data.DataPlan
import com.bening.myplans.databinding.ListPlansBinding
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class PlanAdapter(
    private val onDelete: (DataPlan) -> Unit,
    private val onClick: (DataPlan) -> Unit
): RecyclerView.Adapter<ViewHolder<ListPlansBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListPlansBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListPlansBinding>, position: Int) {
        with(holder.binding) {
            tvTitle.text = data[position].name
            btnDelete.onClick {
                onDelete.invoke(data[position])
                delete(data[position])
            }

            root.onClick {
                onClick.invoke(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addData(data: DataPlan) {
        this.data.add(data)
        notifyDataSetChanged()
    }

    fun getList() : ArrayList<DataPlan> {
        return ArrayList(this.data)
    }

    fun delete(data: DataPlan){
        this.data.remove(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
    }

    val data: MutableList<DataPlan> by lazy {
        ArrayList()
    }
}