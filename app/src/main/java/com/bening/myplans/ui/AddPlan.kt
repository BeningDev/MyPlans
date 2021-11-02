package com.bening.myplans.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bening.myplans.R
import com.bening.myplans.databinding.FragmentAddPlanBinding
import com.bening.myplans.helper.DatabaseHelper
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class AddPlan : Fragment() {

    private val binding: FragmentAddPlanBinding by viewBinding()
    internal val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper(requireContext())
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSave.onClick {
                if ( etPlanName.text.toString().isEmpty() || etPlanDesc.text.toString().isEmpty() ) {
                    toast("Lengkapi semua kolom")
                } else {
                    dbHelper.addPlan(etPlanName.text.toString(), etPlanDesc.text.toString())
                    toast("Plan ditambahkan")
                    etPlanName.setText("")
                    etPlanDesc.setText("")
                }
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
}