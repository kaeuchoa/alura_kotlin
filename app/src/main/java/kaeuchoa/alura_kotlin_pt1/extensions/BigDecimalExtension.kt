package kaeuchoa.alura_kotlin_pt1.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formataParaMoedaBR(): String {
    val numberFormat : NumberFormat = DecimalFormat
            .getCurrencyInstance(Locale("pt", "br"))
    return numberFormat
            .format(this)
            .replace("R$", "R$ ")
            .replace("-R$ ", "R$ -")
}