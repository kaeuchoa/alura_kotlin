package kaeuchoa.alura_kotlin_pt1.extensions

fun String.limitaEmAte(caracteres: Int) : String{
    val primeiroCaracter = 0
    if(this.length > caracteres)
        return "${this.substring(primeiroCaracter,caracteres)}..."

    return this
}