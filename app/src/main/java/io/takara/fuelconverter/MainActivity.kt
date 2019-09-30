package io.takara.fuelconverter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.text.*
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.dynamic_layout_result_label.*
import kotlinx.android.synthetic.main.fuel_converter_layout.*
import kotlin.math.roundToLong
//import io.takara.fuelconverter.EditTextWithWatcher

class MainActivity : AppCompatActivity() , StaticFragment.SendResultListener{

    lateinit var rootFuelConverterLayout: LinearLayout
    lateinit var utils: Utils
    var dynamicFragment = DynamicFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
            Informando qual o arquivo de layout será inflate nessa activity:
                - Arquivo R - Mapeamentos de diretorio do projeto
                - R.id.. identificadores dos elementos
                - R.layout...  Arquivos de layout
                - R.string.... Arquivos de textos
                - R.color... ARquivos de cor
                - R.dimen... Arquivos de dimensao
                - R.style... arquivos de folha de estilos
                - R.value... arquivos de parametros
        */
        setContentView(R.layout.fuel_converter_layout)
        supportFragmentManager.beginTransaction()
            .add(R.id.dynamicFragment, dynamicFragment, "dynamic_fragment_layout")
            .commit()

        utils = Utils()
        rootFuelConverterLayout = findViewById(R.id.FuelConverterLayout)
        utils.hideKeyBoard(rootFuelConverterLayout)
    }


    override fun sendResult(result: String?) {
        dynamicFragment.showResultOnTextView(result)
    }


}

