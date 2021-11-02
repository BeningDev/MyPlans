package com.bening.myplans.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bening.myplans.R
import com.bening.myplans.adapter.PlanAdapter
import com.bening.myplans.data.DataPlan
import com.bening.myplans.databinding.FragmentMyPlansBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding

class MyPlans : Fragment() {

    private val binding: FragmentMyPlansBinding by viewBinding()

    internal val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper(requireContext())
    }

    val adapter: PlanAdapter by lazy {
        PlanAdapter { dataPlan ->
            dbHelper.delPlans(dataPlan.id)
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            myPlans.also{
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            val listPlans = dbHelper.getPlans()

            while (listPlans.moveToNext()) {
                val newPlan = DataPlan(listPlans.getString(0).toInt(), listPlans.getString(1).toString(), listPlans.getString(2).toString())
                adapter.addData(newPlan)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        val listPlans = dbHelper.getPlans()

        while (listPlans.moveToNext()) {
            val newPlan = DataPlan(listPlans.getString(0).toInt(), listPlans.getString(1).toString(), listPlans.getString(2).toString())
            adapter.addData(newPlan)
        }
    }

}