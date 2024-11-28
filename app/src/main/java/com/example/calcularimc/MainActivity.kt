package com.example.calcularimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button);

        val pesoInput: TextInputEditText = findViewById(R.id.pesoInput);

        val alturaInput: TextInputEditText = findViewById(R.id.alturaInput);

        val pesoInputLayout: TextInputLayout = findViewById(R.id.pesoInputLayout);

        val alturaInputLayout: TextInputLayout = findViewById(R.id.alturaInputLayout);

        button.setOnClickListener {
            val valorPeso = pesoInput.text.toString();
            val valorAltura = alturaInput.text.toString();

            if (valorPeso == "") {
                pesoInputLayout.error = "O campo Peso não pode estar vazio!";
                return@setOnClickListener;
            }
            if (valorAltura == "") {
                alturaInputLayout.error = "O campo Altura não pode estar vazio!";
                return@setOnClickListener;
            }
            calcularImc(valorAltura.toDouble(), valorPeso.toDouble());
        }
    }

    private fun calcularImc(valorAltura: Double, valorPeso: Double) {
        val result: Double = valorPeso / (valorAltura * valorAltura);

        val textResult = when {
            result < 18.5 -> "Abaixo do peso!"
            result < 25   -> "Peso normal!"
            result < 30   -> "Sobrepeso!"
            result >= 30  -> "Obesidade!"
                    else  -> ""
        }

        val objetoResult = Resultado(result, valorPeso, valorAltura, textResult);

        val intent = Intent(this, DetalhesCalculo::class.java);

        intent.putExtra("result", objetoResult);

        startActivity(intent);
    }
}