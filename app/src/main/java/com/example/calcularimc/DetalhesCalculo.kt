package com.example.calcularimc

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class DetalhesCalculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var textViewResult: TextView;
        lateinit var textViewPeso: TextView;
        lateinit var textViewAltura: TextView;
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes_calculo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras;
        if (bundle != null) {
            val value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("result", Resultado::class.java);
            } else {
                bundle.getParcelable("result");
            }

            if (value != null) {
                textViewPeso = findViewById(R.id.textViewPeso);
                textViewPeso.setText("Peso: " + value.peso.toString() + " kg");

                textViewAltura = findViewById(R.id.textViewAltura);
                textViewAltura.setText("Altura: " + value.altura.toString() + " m");

                val df = DecimalFormat("#.##")
                val imcRefac = df.format(value.imc);

                textViewResult = findViewById(R.id.textViewResult);
                textViewResult.setText(value.resultado + " Imc: " + imcRefac.toString());
            }
        }

        val buttonSair: Button;
        buttonSair = findViewById(R.id.exitButton);
        buttonSair.setOnClickListener {
            finish();
        }
    }
}