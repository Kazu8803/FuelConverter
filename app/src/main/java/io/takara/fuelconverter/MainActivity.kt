package io.takara.fuelconverter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlin.math.roundToLong
//import io.takara.fuelconverter.EditTextWithWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var fuelConverterLayout: LinearLayout
    private lateinit var calculatorButton: Button
    private lateinit var gasolinePriceEditText: EditText
    private lateinit var ethanolPriceEditText: EditText
    private lateinit var labelTextResult: TextView

    fun turnPriceStringIntoDouble(priceString: String): Double {
        return priceString
            .replace("R$ ", "")
            .replace(".", "")
            .toDouble()
    }

    fun View.hideKeyBoard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun hideKeyboardListener(linearLayout: LinearLayout){
        linearLayout.setOnClickListener{
            linearLayout.hideKeyBoard()
        }
    }

    fun inputFuelPriceMask(elEditText: EditText){

        elEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                elEditText.setSelection(p0.length -1)
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                elEditText.removeTextChangedListener(this)
                val decimalNumber = turnPriceStringIntoDouble( elEditText.text.toString() ) / 100
                val priceString: String = "R$ " + "%.2f".format(decimalNumber).replace(",",".")
                elEditText.setText(priceString)
                elEditText.addTextChangedListener(this)
            }
        })
    }

    fun calculateEthanolGasolineRate(ethanolPrice:Double, gasolinePrice:Double) : Long {
        var percent : Double = (ethanolPrice / gasolinePrice) * 100
        return percent.roundToLong()
    }

    fun showResultRate(calculatorButton: Button, labelTextResult: TextView,
                       gasolinePriceEditText: EditText, ethanolPriceEditText: EditText){
        calculatorButton.setOnClickListener {
            gasolinePriceEditText.hideKeyBoard()
            ethanolPriceEditText.hideKeyBoard()
            val gasolinePrice: Double = turnPriceStringIntoDouble(gasolinePriceEditText.text.toString())
            val ethanolPrice: Double = turnPriceStringIntoDouble(ethanolPriceEditText.text.toString())
            val rate : Long

            if(ethanolPrice.equals(0.0) or gasolinePrice.equals(0.0))
                labelTextResult.text = "O preço de nenhum dos \ncombustíveis pode ser zero."
            else {
                rate = calculateEthanolGasolineRate( ethanolPrice , gasolinePrice)
                if (rate > 70)
                    labelTextResult.text = "Gasolina é mais viável em\n" + rate + "% do preço."
                else
                    labelTextResult.text = "Etanol é mais viável em\n" + rate + "% do preço."
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*informando qual o arquivo de layout será inflate nessa activity
        Arquivo R - Mapeamentos de diretorio do projeto
        R.id.. identificadores dos elementos
        R.layout...  Arquivos de layout
        R.string.... Arquivos de textos
        R.color... ARquivos de cor
        R.dimen... Arquivos de dimensao
        R.style... arquivos de folha de estilos
        R.value... arquivos de parametros
        */
        setContentView(R.layout.fuel_converter_layout)

        fuelConverterLayout = findViewById(R.id.FuelConverterLayout)
        gasolinePriceEditText = findViewById(R.id.gasolinePrice)
        ethanolPriceEditText = findViewById(R.id.ethanolPrice)
        calculatorButton = findViewById(R.id.calculatorButton)
        labelTextResult = findViewById(R.id.textResult)

        hideKeyboardListener(fuelConverterLayout)
        inputFuelPriceMask(gasolinePriceEditText)
        inputFuelPriceMask(ethanolPriceEditText)
        showResultRate(calculatorButton, labelTextResult, gasolinePriceEditText, ethanolPriceEditText)
    }
}
