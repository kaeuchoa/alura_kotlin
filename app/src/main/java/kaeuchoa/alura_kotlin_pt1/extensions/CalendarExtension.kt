package kaeuchoa.alura_kotlin_pt1.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String {
    val formatoData = "dd/MM/yyyy"
    val formatDate = SimpleDateFormat(formatoData)
    return formatDate.format(this.time)
}