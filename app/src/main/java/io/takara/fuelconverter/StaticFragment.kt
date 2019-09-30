package io.takara.fuelconverter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.static_layout_title_prices_button_calculator.*
import kotlin.math.roundToLong

class StaticFragment : Fragment(){
    lateinit var priceEditTextInteraction: PriceEditTextInteraction
    lateinit var gasolineEditText: EditText
    lateinit var ethanolEditText: EditText
    lateinit var buttonCalculator: Button

    var listener: SendResultListener? = null

    companion object{
        fun newInstance() : StaticFragment {
            return StaticFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SendResultListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.static_layout_title_prices_button_calculator,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var utils : Utils = Utils()
        priceEditTextInteraction = PriceEditTextInteraction()

        gasolineEditText = view.findViewById(R.id.gasolinePrice)
        ethanolEditText = view.findViewById(R.id.ethanolPrice)

        buttonCalculator = view.findViewById(R.id.calculatorButton)

        priceEditTextInteraction.inputFuelPriceMask(gasolineEditText)
        priceEditTextInteraction.inputFuelPriceMask(ethanolEditText)

       buttonCalculator.setOnClickListener {
           utils.hideKeyBoard(buttonCalculator)
           listener?.sendResult(utils.getRate(gasolineEditText, ethanolEditText))
       }

    }

    interface SendResultListener {
        fun sendResult(result: String?)
    }


}