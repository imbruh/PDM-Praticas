package com.example.wishlist

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvDesejos: ListView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: ArrayList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = arrayListOf()

        this.lvDesejos = findViewById(R.id.lvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.lvDesejos.adapter = ArrayAdapter<Desejo>(this, android.R.layout.simple_list_item_1, this.lista)

        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo
                (this.lvDesejos.adapter as ArrayAdapter<Desejo>).add(desejo)
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            resultForm.launch(intent)
        }

        this.lvDesejos.setOnItemClickListener(OnItemClick())
        this.lvDesejos.setOnItemLongClickListener(OnItemLongClick())
    }

    inner class OnItemClick: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val desejo = this@MainActivity.lista[position]
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            var desejo = lista.get(position)
            dialogExcluir(this@MainActivity, position, desejo)
            return true
        }
    }

    fun dialogExcluir(activity: Activity, position: Int, desejo: Desejo) {
        AlertDialog.Builder(activity).apply {
            val dialogView = activity.layoutInflater.inflate(R.layout.support_simple_spinner_dropdown_item, null)

            setView(dialogView)

            setTitle("Excluir Item")
            setMessage("Tem certeza que deseja excluir o item " + desejo.toString())
            setPositiveButton("Excluir") { _, _ ->
                this@MainActivity.lista.removeAt(position)
                (this@MainActivity.lvDesejos.adapter as ArrayAdapter<Desejo>).notifyDataSetChanged()
            }

            setNegativeButton("Cancelar") { _, _ ->
                Toast.makeText(this@MainActivity, "Operação cancelada", Toast.LENGTH_SHORT).show()
            }
        }.create().show()
    }
}