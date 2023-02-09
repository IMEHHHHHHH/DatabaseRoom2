package com.example.sumatifroom_alfinaimah_ganjil.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class tb_karyawan(
    @PrimaryKey(autoGenerate = true)
    val ID : Int,
    val Nama : String,
    val Alamat : String,
    val Usia : String)