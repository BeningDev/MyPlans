package com.bening.myplans.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bening.myplans.R
import com.bening.myplans.databinding.FragmentFinishPlanBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding

class FinishPlan : Fragment() {

    private val binding: FragmentFinishPlanBinding by viewBinding()

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish_plan, container, false)
    }
}