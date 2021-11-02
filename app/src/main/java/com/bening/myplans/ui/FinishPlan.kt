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
import com.bening.myplans.databinding.FragmentFinishPlanBinding
import com.bening.myplans.helper.DatabaseHelper
import com.bening.myplans.main.ShowPlan
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.startActivity

class FinishPlan : Fragment() {

    private val binding: FragmentFinishPlanBinding by viewBinding()

    internal val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper(requireContext())
    }

    val adapter: PlanAdapter by lazy {
        PlanAdapter ({ dataPlan ->
            dbHelper.delPlans(dataPlan.id)
        }, { dataPlan ->
            startActivity(ShowPlan::class.java) {
                it.putExtra("data", dataPlan)
            }
        })
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

            val listPlans = dbHelper.getPlansFinish()

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
        val listPlans = dbHelper.getPlansFinish()

        while (listPlans.moveToNext()) {
            val newPlan = DataPlan(listPlans.getString(0).toInt(), listPlans.getString(1).toString(), listPlans.getString(2).toString())
            adapter.addData(newPlan)
        }
    }
}