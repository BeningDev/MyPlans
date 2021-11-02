package com.bening.myplans.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bening.myplans.data.DataProblem
import com.bening.myplans.databinding.ListProblemsBinding
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class ProblemAdapter(
    private val onClick: (DataProblem) -> Unit
): RecyclerView.Adapter<ViewHolder<ListProblemsBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListProblemsBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListProblemsBinding>, position: Int) {
        with(holder.binding) {
            tvTitle.text = data[position].name
            tvDesc.text = data[position].desc
            if ( data[position].status == "active" ) {
                tvStatusActive.visibility = View.VISIBLE
                tvStatusSolved.visibility = View.GONE
            } else {
                tvStatusActive.visibility = View.GONE
                tvStatusSolved.visibility = View.VISIBLE
            }

            root.onClick {
                onClick.invoke(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun addData(data: DataProblem) {
        this.data.add(data)
        notifyDataSetChanged()
    }

    fun getList() : ArrayList<DataProblem> {
        return ArrayList(this.data)
    }

    fun delete(data: DataProblem){
        this.data.remove(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
    }

    val data: MutableList<DataProblem> by lazy {
        ArrayList()
    }
}