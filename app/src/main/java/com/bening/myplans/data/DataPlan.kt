package com.bening.myplans.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlan(
    var id: Int,
    var name: String,
    var desc: String
) : Parcelable {
    fun simpleDesc(): String {
        return "hay"
    }
}