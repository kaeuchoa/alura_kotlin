package kaeuchoa.alura_kotlin_pt1.models

import java.math.BigDecimal
import java.util.Calendar

class Transacao (val valor: BigDecimal,
                 val categoria: String = "Indefinida",
                 val data : Calendar = Calendar.getInstance(),
                 val tipo : TipoTransacao)