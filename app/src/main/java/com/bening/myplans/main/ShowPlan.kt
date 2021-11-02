package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bening.myplans.R
import com.bening.myplans.data.DataPlan
import com.bening.myplans.databinding.ActivityShowPlanBinding
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick

class ShowPlan : AppCompatActivity() {

    private val binding: ActivityShowPlanBinding by viewBinding()

    val dataPlan: DataPlan by intent("data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_plan)

        with(binding) {
            tvTitle.setText(dataPlan.name)
            tvDesc.setText(dataPlan.desc)

            btnBack.onClick {
                finish()
            }
        }
    }
}