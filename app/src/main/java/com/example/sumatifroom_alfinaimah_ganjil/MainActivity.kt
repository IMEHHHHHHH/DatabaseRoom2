package com.example.sumatifroom_alfinaimah_ganjil

import android.content.Intent
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_alfinaimah_ganjil.Room.Constant
import com.example.sumatifroom_alfinaimah_ganjil.Room.codepelita
import com.example.sumatifroom_alfinaimah_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val db by lazy { codepelita(this) }
    private lateinit var adapterKaryawan: KryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loaddata()
    }
    fun loaddata(){
        CoroutineScope(Dispatchers.IO).launch {
            val karyawan = db.karyawanDAO().dapattb_karyawan()
            Log.d("MainActivity", "dbResponse:$karyawan")
            withContext(Dispatchers.Main){
                adapterKaryawan.setData(karyawan)
            }
        }
    }
    fun intentEdit(noteId:Int,intentType:Int){
        val pindah = Intent(applicationContext,EditActivity::class.java)
        startActivity(pindah
            .putExtra("intent_id",noteId)
            .putExtra("intent_type",intentType)
        )
    }
    private fun setUpListener(){
        inputdata.setOnClickListener{
           intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    private fun setupRecyclerView(){
        adapterKaryawan = KryAdapter(arrayListOf(), object :KryAdapter.onAdapterListener{
            override fun onClick(tbkry: tb_karyawan) {
                intentEdit(tbkry.ID,Constant.TYPE_READ)

            }

            override fun onUpdate(tbkry: tb_karyawan) {
                intentEdit(tbkry.ID,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbkry: tb_karyawan) {
                deleteAlert(tbkry)
            }
        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterKaryawan
        }
    }
    private fun deleteAlert(tbKrywn : tb_karyawan){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Delete Confirmation")
            setMessage("Sure Delete ${tbKrywn.Nama}?")
            setNegativeButton("Cancel"){ dialogInterface,i->
                dialogInterface.dismiss()
            }
            setPositiveButton("Delete"){dialogInterface,i->
                CoroutineScope(Dispatchers.IO).launch {
                    db.karyawanDAO().deletetb_karyawan(tbKrywn)
                    dialogInterface.dismiss()
                    loaddata()
                }
            }
        }
        dialog.show()
    }
}


