package io.takara.fuelconverter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PriceEditTextInteraction {
    val utils: Utils = Utils()

    fun inputFuelPriceMask(editText: EditText){
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                editText.removeTextChangedListener(this)
                val decimalNumber = utils.turnPriceStringIntoDouble( editText.text.toString() ) / 100
                val priceString: String = "R$ " + "%.2f".format(decimalNumber).replace(",",".")
                editText.setText(priceString)
                editText.addTextChangedListener(this)
            }
        })
    }

}