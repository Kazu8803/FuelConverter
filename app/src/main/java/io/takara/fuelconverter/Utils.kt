package io.takara.fuelconverter

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import kotlin.math.roundToLong

class Utils() {

    fun turnPriceStringIntoDouble(priceString: String): Double {
        return priceString
            .replace("R$ ", "")
            .replace(".", "")
            .toDouble()
    }

    private fun calculateEthanolGasolineRate(ethanolPrice:Double, gasolinePrice:Double) : Long {
        var percent : Double = (ethanolPrice / gasolinePrice) * 100
        return percent.roundToLong()
    }

    fun getRate(gasolineEditText:EditText, ethanolEditText: EditText): String{
        val gasolinePrice: Double = turnPriceStringIntoDouble(gasolineEditText.text.toString())
        val ethanolPrice: Double = turnPriceStringIntoDouble(ethanolEditText.text.toString())
        val rate: Long
        var result: String
        if (ethanolPrice.equals(0.0) or gasolinePrice.equals(0.0))
            result = "O preço de nenhum dos \ncombustíveis pode ser zero."
        else {
            rate = calculateEthanolGasolineRate(ethanolPrice, gasolinePrice)
            if (rate > 70.0)
                result = "Gasolina é mais viável em\n" + rate + "% do preço."
            else
                result = "Etanol é mais viável em\n" + rate + "% do preço."
        }
        return result
    }

    fun View.hideKeyBoard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun hideKeyBoard(linearLayout: LinearLayout){
        linearLayout.setOnClickListener{
            linearLayout.hideKeyBoard()
        }
    }

    fun hideKeyBoard(button: Button){
            button.hideKeyBoard()
    }

}