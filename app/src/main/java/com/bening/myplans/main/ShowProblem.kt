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

            if ( dataProblem.status == "active" ) {
                btnSolved.hint = "solved"
            } else {
                btnSolved.hint = "unsolved"
            }

            btnSolved.onClick {

                if ( dataProblem.status == "active" ) {
                    dbHelper.solvedProblem(dataProblem.id)
                    toast("Problem solved")
                } else {
                    dbHelper.unSolvedProblem(dataProblem.id)
                    toast("Problem unsolved")
                }

                finish()
            }

            btnSave.onClick {
                if (etProblemName.text.toString().isNotEmpty() && etProblemDesc.text.toString().isNotEmpty()) {
                    dbHelper.updateProblem(dataProblem.id, etProblemName.text.toString(), etProblemDesc.text.toString())
                    toast("Noted Update")
                    finish()
                } else {
                    toast("Silahkan isi semua kolom")
                }
            }

            btnBack.onClick {
                finish()
            }
        }
    }
}