package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bening.myplans.R
import com.bening.myplans.data.DataPlan
import com.bening.myplans.databinding.ActivityAddProblemBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class AddProblem : AppCompatActivity() {

    private val binding: ActivityAddProblemBinding by viewBinding()
    internal val dbHelper = DatabaseHelper(this)

    val dataPlan: DataPlan by intent("data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_problem)

        with(binding) {
            btnBack.onClick {
                finish()
            }

            btnSave.onClick {
                dbHelper.addProblem(etProblemName.text.toString(), etProblemDesc.text.toString(), dataPlan.id)
                toast("Problem ditambahkan")
                finish()
            }
        }
    }
}