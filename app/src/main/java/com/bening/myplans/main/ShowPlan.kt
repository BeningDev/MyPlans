package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bening.myplans.R
import com.bening.myplans.adapter.ProblemAdapter
import com.bening.myplans.data.DataPlan
import com.bening.myplans.data.DataProblem
import com.bening.myplans.databinding.ActivityShowPlanBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast

class ShowPlan : AppCompatActivity() {

    private val binding: ActivityShowPlanBinding by viewBinding()

    internal val dbHelper = DatabaseHelper(this)

    val dataPlan: DataPlan by intent("data")

    val adapter: ProblemAdapter by lazy {
        ProblemAdapter { dataProblem ->
            startActivity(ShowProblem::class.java) {
                it.putExtra("data", dataProblem)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_plan)

        with(binding) {
            tvTitle.setText(dataPlan.name)
            tvDesc.setText(dataPlan.desc)

            if ( dataPlan.status != "finish" ) {
                btnFinish.hint = "Finish Plan"
            } else {
                btnFinish.hint = "Un finish Plan"
            }

            btnBack.onClick {
                finish()
            }

            btnFinish.onClick {
                if ( dataPlan.status != "finish" ) {
                    dbHelper.finishPlan(dataPlan.id)
                    toast("Plan Telah Selesai")
                } else {
                    dbHelper.unFinishPlan(dataPlan.id)
                    toast("Plan belum ter-selesai")
                }
            }

            btnAddProblem.onClick {
                startActivity(AddProblem::class.java) {
                    it.putExtra("data", dataPlan)
                }
            }

            myProblems.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    this@ShowPlan,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            var listProblem = dbHelper.getProblems(dataPlan.id)

            while (listProblem.moveToNext()) {
                val newProblem = DataProblem(listProblem.getString(0).toInt(), listProblem.getString(1).toString(), listProblem.getString(2).toString(), listProblem.getString(3).toString())
                adapter.addData(newProblem)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        var listProblem = dbHelper.getProblems(dataPlan.id)

        while (listProblem.moveToNext()) {
            val newProblem = DataProblem(listProblem.getString(0).toInt(), listProblem.getString(1).toString(), listProblem.getString(2).toString(), listProblem.getString(3).toString())
            adapter.addData(newProblem)
        }
    }
}