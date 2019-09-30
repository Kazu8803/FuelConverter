package io.takara.fuelconverter

import android.arch.lifecycle.ViewModelProvider
import android.media.audiofx.DynamicsProcessing
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DynamicFragment : Fragment() {
    lateinit var resultTextViewInteraction: ResultTextViewInteraction
    lateinit var resultTextView: TextView

    companion object{
        fun newInstance() : DynamicFragment {
            return DynamicFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dynamic_layout_result_label,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultTextViewInteraction = ResultTextViewInteraction()
        resultTextView = view.findViewById(R.id.textResult)
    }

    fun showResultOnTextView(result: String?){
        resultTextViewInteraction.showCalculationResult(resultTextView, result)
    }

}
