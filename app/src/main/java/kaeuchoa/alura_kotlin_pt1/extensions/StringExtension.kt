package kaeuchoa.alura_kotlin_pt1.extensions

import kotlinx.android.synthetic.main.form_transacao.view.*
import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int) : String{
    val primeiroCaracter = 0
    if(this.length > caracteres)
        return "${this.substring(primeiroCaracter,caracteres)}..."

    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val data: Date = formatoBrasileiro.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = data
    return calendar
}