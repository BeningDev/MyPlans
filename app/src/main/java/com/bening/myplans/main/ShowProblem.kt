package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bening.myplans.R
import com.bening.myplans.data.DataProblem
import com.bening.myplans.databinding.ActivityShowProblemBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class ShowProblem : AppCompatActivity() {

    val dataProblem: DataProblem by intent("data")
    private val binding: ActivityShowProblemBinding by viewBinding()
    private val dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_problem)

        with(binding) {
            etProblemName.setText(dataProblem.name)
            etProblemDesc.setText(dataProblem.desc)

            btnSolved.onClick {
                dbHelper.solvedProblem(dataProblem.id)
                toast("Problem solved")
                finish()
            }

            btnBack.onClick {
                finish()
            }
        }
    }
}