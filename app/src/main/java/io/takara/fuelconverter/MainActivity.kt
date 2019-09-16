package io.takara.fuelconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button

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
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this, "Hello world", Toast.LENGTH_SHORT).show()
        }
    }
}
