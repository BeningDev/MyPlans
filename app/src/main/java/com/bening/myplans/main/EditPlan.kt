package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bening.myplans.R
import com.bening.myplans.data.DataPlan
import com.bening.myplans.databinding.ActivityEditPlanBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class EditPlan : AppCompatActivity() {

    private val binding: ActivityEditPlanBinding by viewBinding()
    private val dbHelper = DatabaseHelper(this)

    val dataPlan: DataPlan by intent("data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plan)

        with(binding) {
            etPlanName.setText(dataPlan.name)
            etPlanDesc.setText(dataPlan.desc)

            btnBack.onClick {
                finish()
            }

            btnSave.onClick {
                if (etPlanName.text.toString().isNotEmpty() && etPlanDesc.text.toString().isNotEmpty()) {
                    dbHelper.updatePlan(dataPlan.id, etPlanName.text.toString(), etPlanDesc.text.toString())
                    toast("Plan Update")
                    finish()
                } else {
                    toast("Pastikan semua kolom terisi")
                }
            }
        }
    }
}