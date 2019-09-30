package io.takara.fuelconverter

import android.util.Log
import android.widget.TextView

class ResultTextViewInteraction {
    fun showCalculationResult(textView: TextView, result: String?){
        textView.setText(result)
    }
}