package com.example.sumatifroom_alfinaimah_ganjil.Room

import androidx.room.*

@Dao
interface karyawanDAO{
    @Insert
    fun addtb_karyawan (tbKry : tb_karyawan)
    @Update
    fun updatetb_karyawan(tbKry: tb_karyawan)
    @Delete
    fun deletetb_karyawan(tbKry: tb_karyawan)
    @Query("SELECT * FROM tb_karyawan")
    fun dapattb_karyawan(): List<tb_karyawan>
    @Query("SELECT * FROM tb_karyawan WHERE ID=:tbKaryawan_nis")
    fun dapatkantb_karyawan(tbKaryawan_nis : Int): List<tb_karyawan>
}