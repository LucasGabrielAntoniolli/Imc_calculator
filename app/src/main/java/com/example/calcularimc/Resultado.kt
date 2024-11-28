package com.example.calcularimc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Resultado (
    val imc: Double,
    val peso: Double,
    val altura: Double,
    val resultado: String
): Parcelable