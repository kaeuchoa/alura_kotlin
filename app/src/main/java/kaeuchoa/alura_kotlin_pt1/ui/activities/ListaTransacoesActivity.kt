package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.app.DatePickerDialog
import android.content.DialogInterface
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
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val listaTransacoes : MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()

        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val decorView: View = window.decorView
        val resumoView = ResumoView(this,decorView, listaTransacoes)
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
                    .setPositiveButton("Adicionar") { dialog, i ->
                        val valorEmTexto = viewFormulario.form_transacao_valor.text.toString()

                        val valor = try {
                            BigDecimal(valorEmTexto)
                        }catch (e : NumberFormatException){
                            Toast.makeText(this,"Erro na conversão do valor", Toast.LENGTH_LONG).show()
                            BigDecimal.ZERO
                        }

                        val dataEmTexto = viewFormulario.form_transacao_data.text.toString()
                        val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
                        val data: Date = formatoBrasileiro.parse(dataEmTexto)
                        val calendar = Calendar.getInstance()
                        calendar.time = data

                        val categoriaEmTexto = viewFormulario.form_transacao_categoria.selectedItem.toString()

                        val novaTransacao = Transacao(valor = valor, categoria = categoriaEmTexto, data = calendar, tipo = TipoTransacao.RECEITA)

                        atualizaTransacoes(novaTransacao)


                    }
                    .setNegativeButton("Cancelar",null)
                    .setView(viewFormulario)
                    .show()
        }
    }

    private fun atualizaTransacoes(novaTransacao: Transacao) {
        listaTransacoes.add(novaTransacao)
        configuraResumo()
        configuraListaTransacoes()
        lista_transacoes_adiciona_menu.close(true)
    }


    private fun configuraListaTransacoes() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }



}