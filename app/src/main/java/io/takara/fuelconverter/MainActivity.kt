package io.takara.fuelconverter

import android.content.Context
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fuel_converter_layout.*
import kotlin.math.log
import kotlin.math.roundToInt
import kotlin.math.roundToLong
//import io.takara.fuelconverter.EditTextWithWatcher

class MainActivity : AppCompatActivity() {

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

    fun forceCursorAtStringEnd(elEditText: EditText){
        val cursorWatcher = object: SpanWatcher {
            override fun onSpanAdded(text: Spannable, what: Any, start: Int, end: Int){}
            override fun onSpanRemoved(text: Spannable, what: Any, start: Int, end: Int){}
            override fun onSpanChanged(text: Spannable, what: Any, ostart: Int, oend: Int, nstart: Int, nend: Int) {
                    elEditText.setSelection(elEditText.length())
            }
        }
        elEditText.getText().setSpan(cursorWatcher, 0, 0, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    fun forceCursorAtTextEnd(elEditText: EditText){
        elEditText.setOnClickListener{
            elEditText.setSelection(elEditText.length())
        }
    }

    fun inputFuelPriceMask(elEditText: EditText){
        elEditText.setOnClickListener{ elEditText.setSelection(elEditText.length()) }

        elEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                forceCursorAtStringEnd(elEditText)
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
            val gasolinePrice: Double = turnPriceStringIntoDouble(gasolinePriceEditText.text.toString())
            val ethanolPrice: Double = turnPriceStringIntoDouble(ethanolPriceEditText.text.toString())
            val rate = calculateEthanolGasolineRate( ethanolPrice , gasolinePrice)

            if(ethanolPrice.equals(0.0) or gasolinePrice.equals(0.0))
                labelTextResult.text = "O preço de nenhum dos \ncombustíveis pode ser zero."
            else if(rate > 70)
                labelTextResult.text = "Gasolina é mais viável em\n" + rate + "% do preço."
            else
                labelTextResult.text = "Etanol é mais viável em\n" + rate + "% do preço."
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

        gasolinePriceEditText = findViewById(R.id.gasolinePrice)
        ethanolPriceEditText = findViewById(R.id.ethanolPrice)
        calculatorButton = findViewById(R.id.calculatorButton)
        labelTextResult = findViewById(R.id.textResult)
        forceCursorAtTextEnd(gasolinePriceEditText)
        forceCursorAtTextEnd(ethanolPriceEditText)
        inputFuelPriceMask(gasolinePriceEditText)
        inputFuelPriceMask(ethanolPriceEditText)

        showResultRate(calculatorButton, labelTextResult, gasolinePriceEditText, ethanolPriceEditText)

    }
}
