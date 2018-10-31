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
import kaeuchoa.alura_kotlin_pt1.R.id.lista_transacoes_adiciona_menu
import kaeuchoa.alura_kotlin_pt1.delegate.TransacaoDelegate
import kaeuchoa.alura_kotlin_pt1.extensions.converteParaCalendar
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(private val viewGroup: ViewGroup,
                              private val context: Context) {

    private val viewFormulario: View = criaLayout()
    fun configuraDialog(transacaoDelegate: TransacaoDelegate){
        configuraCampoData()
        configuraCampoCategoria()
        configuraFormulario(transacaoDelegate)
    }

    private fun configuraFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
                .setTitle(R.string.adiciona_receita)
                .setPositiveButton("Adicionar") { _, _ ->
                    val valorEmTexto = viewFormulario.form_transacao_valor.text.toString()

                    val valor = converteCampoValor(valorEmTexto)

                    val dataEmTexto = viewFormulario.form_transacao_data.text.toString()
                    val calendar = dataEmTexto.converteParaCalendar()
                    val categoriaEmTexto = viewFormulario.form_transacao_categoria.selectedItem.toString()

                    val novaTransacao = Transacao(valor = valor, categoria = categoriaEmTexto, data = calendar, tipo = TipoTransacao.RECEITA)

                    transacaoDelegate.delegate(novaTransacao)


                }
                .setNegativeButton("Cancelar", null)
                .setView(viewFormulario)
                .show()
    }



    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (e: NumberFormatException) {
            Toast.makeText(context, "Erro na conversão do valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item)
        viewFormulario.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val dataAtual = Calendar.getInstance()
        val dia = dataAtual[Calendar.DAY_OF_MONTH]
        val mes = dataAtual[Calendar.MONTH]
        val ano = dataAtual[Calendar.YEAR]

        viewFormulario.form_transacao_data.setText(dataAtual.formataParaBrasileiro())
        viewFormulario.form_transacao_data.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(year, month, dayOfMonth)
                viewFormulario.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
            }, ano, mes, dia).show()
        }
    }

    private fun criaLayout(): View {
        val viewFormulario = LayoutInflater.from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)
        return viewFormulario
    }
}