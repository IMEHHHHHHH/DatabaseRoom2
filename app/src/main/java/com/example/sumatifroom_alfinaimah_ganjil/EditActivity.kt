package com.example.sumatifroom_alfinaimah_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_alfinaimah_ganjil.Room.Constant
import com.example.sumatifroom_alfinaimah_ganjil.Room.codepelita
import com.example.sumatifroom_alfinaimah_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private val db by lazy { codepelita(this) }
    private var noteID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupview()
        noteID = intent.getIntExtra("intent_id",0)
        Toast.makeText(this,noteID.toString(),Toast.LENGTH_SHORT).show()
    }
    fun setupview(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType){
            Constant.TYPE_CREATE->{
                buttonupdate.visibility = View.GONE

            }
            Constant.TYPE_UPDATE-> {
                buttonsave.visibility = View.GONE
                membaca()
            }
            Constant.TYPE_READ->{
                buttonsave.visibility = View.GONE
                buttonupdate.visibility = View.GONE
                membaca()
            }
        }
    }
    fun tombolperintah(){
        buttonsave.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.karyawanDAO().addtb_karyawan(
                    tb_karyawan(et_id.text.toString().toInt(),et_name.text.toString(),et_almt.text.toString(),et_usia.text.toString())
                )
                finish()
            }
        }
        buttonupdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.karyawanDAO().updatetb_karyawan(
                    tb_karyawan(et_id.text.toString().toInt(),et_name.text.toString(),
                    et_almt.text.toString(),et_usia.text.toString())
                )
                finish()
            }
        }
    }
    fun membaca(){
        noteID = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val tbNote = db.karyawanDAO().dapatkantb_karyawan(noteID)[0]
            val dataId : String = tbNote.ID.toString()
            et_id.setText(dataId)
            et_name.setText(tbNote.Nama)
            et_almt.setText(tbNote.Alamat)
            et_usia.setText(tbNote.Usia)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}