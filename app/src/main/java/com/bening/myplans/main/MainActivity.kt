package com.bening.myplans.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bening.myplans.R
import com.bening.myplans.databinding.ActivityMainBinding
import com.bening.myplans.ui.AddPlan
import com.bening.myplans.ui.FinishPlan
import com.bening.myplans.ui.MyPlans
import com.oratakashi.viewbinding.core.binding.activity.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            openFragment(MyPlans(), "My Plans")

            bnHome.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_plan -> {
                        openFragment(MyPlans(), "My Plans")
                        true
                    }
                    R.id.nav_add -> {
                        openFragment(AddPlan(), "Add Plan")
                        true
                    }
                    R.id.nav_finish -> {
                        openFragment(FinishPlan(), "Finish Plan")
                        true
                    }

                    else -> false
                }
            }
        }


    }

    private fun openFragment(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flHome, fragment, name)
            .commit()
    }
}