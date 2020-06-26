package com.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    lateinit var tieValorConta    : TextInputEditText;
    lateinit var tvPercentual     : TextView;
    lateinit var seekBarGorjeta   : SeekBar;
    lateinit var tvValorGorjeta   : TextView;
    lateinit var tvTotal          : TextView;

    private var porcentagem : BigDecimal = BigDecimal.ZERO;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tieValorConta    = findViewById(R.id.tieValorConta);
        tvPercentual     = findViewById(R.id.tvPercentual);
        seekBarGorjeta   = findViewById<SeekBar>(R.id.seekBarGorjeta);
        tvValorGorjeta   = findViewById(R.id.tvValorGorjeta);
        tvTotal          = findViewById(R.id.tvTotal);

        initSeekBarGorjeta();

    }



    private fun initSeekBarGorjeta() {
        this.seekBarGorjeta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                porcentagem = BigDecimal(progress.toString());

                tvPercentual.text = porcentagem.toString().plus(" %")
                calcular();

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun calcular() {
        var valorConta : String = tieValorConta.text.toString();
        var valorGorjeta : BigDecimal = BigDecimal.ZERO;
        var totalConta :  BigDecimal = BigDecimal.ZERO;

        if(valorConta == null || "".equals(valorConta)) {
            Toast.makeText(applicationContext, "Informe o valor da Conta", Toast.LENGTH_LONG).show();
        } else {
            valorGorjeta =  BigDecimal(valorConta).multiply(porcentagem.divide(BigDecimal("100")));
            totalConta = BigDecimal(valorConta).plus(valorGorjeta);

            tvValorGorjeta.text = "R$ $valorGorjeta";
            this.tvTotal.text = "R$ $totalConta";
        }
    }
}
