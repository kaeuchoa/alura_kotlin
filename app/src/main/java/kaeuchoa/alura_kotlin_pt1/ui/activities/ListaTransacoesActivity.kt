package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kaeuchoa.alura_kotlin_pt1.ui.ResumoView
import kaeuchoa.alura_kotlin_pt1.ui.adapters.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = getListaExemplos()

        configuraResumo(transacoes)

        configuraListaTransacoes(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val decorView: View = window.decorView
        val resumoView = ResumoView(this,decorView, transacoes)
        resumoView.atualizaView()

        lista_transacoes_adiciona_receita.setOnClickListener {
            val viewGroup = window.decorView as ViewGroup
            val viewFormulario = LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao, viewGroup, false)

            val data = Calendar.getInstance()
            viewFormulario.form_transacao_data.setText(data.formataParaBrasileiro())
            viewFormulario.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                    view, year, month, dayOfMonth ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year,month,dayOfMonth)
                    viewFormulario.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                },2018,9,23).show()
            }

            val adapter = ArrayAdapter.createFromResource(this,
                    R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item)
            viewFormulario.form_transacao_categoria.adapter = adapter
            AlertDialog.Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar",null)
                    .setView(viewFormulario)
                    .show()
        }
    }


    private fun configuraListaTransacoes(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun getListaExemplos(): List<Transacao> {
        return listOf(
                Transacao(
                        valor = BigDecimal(20.50),
                        tipo = TipoTransacao.DESPESA),
                Transacao(
                        valor = BigDecimal(100),
                        categoria = "Economia",
                        tipo = TipoTransacao.RECEITA),
                Transacao(
                        valor = BigDecimal(50),
                        tipo = TipoTransacao.DESPESA,
                        categoria = "Compras"),
                Transacao(
                        valor = BigDecimal(150.0),
                        tipo = TipoTransacao.RECEITA,
                        categoria = "Bônus"),
                Transacao(
                        valor = BigDecimal(25.35),
                        tipo = TipoTransacao.DESPESA,
                        categoria = "Teste de Categoria com nome grande"
                )
        )
    }

}