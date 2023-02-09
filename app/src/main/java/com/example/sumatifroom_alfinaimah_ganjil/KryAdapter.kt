package com.example.sumatifroom_alfinaimah_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_alfinaimah_ganjil.Room.tb_karyawan
import kotlinx.android.synthetic.main.activity_kry_adapter.view.*

class KryAdapter (private val krywn:ArrayList<tb_karyawan>,private val listener : onAdapterListener):
        RecyclerView.Adapter<KryAdapter.KryViewHolder>(){
                class KryViewHolder(val view : View): RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KryViewHolder {
                return KryViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.activity_kry_adapter,parent,false)
                )
        }

        override fun onBindViewHolder(holder: KryViewHolder, position: Int) {
                val kry = krywn[position]
                holder.itemView.TV_id.text = kry.ID.toString()
                holder.itemView.Tv_nama.text = kry.Nama
                holder.itemView.TV_id.setOnClickListener{
                        listener.onClick(kry)
                }
                holder.view.imageEdit.setOnClickListener{
                        listener.onUpdate(kry)
                }
                holder.view.imageDelete.setOnClickListener{
                        listener.onDelete(kry)
                }
        }

        override fun getItemCount() = krywn.size
        fun setData( list: List<tb_karyawan>){
                krywn.clear()
                krywn.addAll(list)
                notifyDataSetChanged()

        }
        interface onAdapterListener{
                fun onClick(tbkry: tb_karyawan)
                fun onUpdate(tbkry: tb_karyawan)
                fun onDelete(tbkry: tb_karyawan)
        }
}