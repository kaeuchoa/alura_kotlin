package kaeuchoa.alura_kotlin_pt1.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(this.time)
}