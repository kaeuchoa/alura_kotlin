package kaeuchoa.alura_kotlin_pt1.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.delegate.TransacaoDelegate
import kaeuchoa.alura_kotlin_pt1.extensions.converteParaCalendar
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.util.*

open class FormularioTransacaoDialog (private val context: Context,
                                      private val viewGroup: ViewGroup) {
    private val viewFormulario: View = criaLayout()
    private val campoCategoria = viewFormulario.spin_transacao_categoria
    private val campoData = viewFormulario.form_transacao_data
    private val campoValor = viewFormulario.form_transacao_valor
    fun configuraDialog(tipo: TipoTransacao, transacaoDelegate: TransacaoDelegate) {
        configuraCampoData()
        configuraCampoCategoria(tipo)
        configuraFormulario(tipo, transacaoDelegate)
    }

    private fun configuraFormulario(tipo: TipoTransacao, transacaoDelegate: TransacaoDelegate) {
        val titulo = tituloPor(tipo)
        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setPositiveButton("Adicionar") { _, _ ->
                    val valorEmTexto = campoValor.text.toString()

                    val valor = converteCampoValor(valorEmTexto)

                    val dataEmTexto = campoData.text.toString()
                    val calendar = dataEmTexto.converteParaCalendar()
                    val categoriaEmTexto = campoCategoria.selectedItem.toString()

                    val novaTransacao = Transacao(valor = valor, categoria = categoriaEmTexto, data = calendar, tipo = tipo)

                    transacaoDelegate.delegate(novaTransacao)


                }
                .setNegativeButton("Cancelar", null)
                .setView(viewFormulario)
                .show()
    }

    private fun tituloPor(tipo: TipoTransacao): Int {
        if (tipo == TipoTransacao.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa

    }

    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Erro na conversão do valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria(tipo: TipoTransacao) {
        val categorias = categoriaPor(tipo)
        val adapter = ArrayAdapter.createFromResource(context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item)
        campoCategoria.adapter = adapter
    }

    private fun categoriaPor(tipo: TipoTransacao): Int {
        return if (tipo == TipoTransacao.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
    }

    private fun configuraCampoData() {
        val dataAtual = Calendar.getInstance()
        val dia = dataAtual[Calendar.DAY_OF_MONTH]
        val mes = dataAtual[Calendar.MONTH]
        val ano = dataAtual[Calendar.YEAR]

        campoData.setText(dataAtual.formataParaBrasileiro())
        campoData.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(year, month, dayOfMonth)
                campoData.setText(dataSelecionada.formataParaBrasileiro())
            }, ano, mes, dia).show()
        }
    }

    private fun criaLayout(): View {
        val viewFormulario = LayoutInflater.from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)
        return viewFormulario
    }
}