package com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rapido_partners")
data class RapidoPartner(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "earning") val earning: Double?,
    @ColumnInfo(name = "gender") val gender: Char?,
    @ColumnInfo(name = "age") val age: Int?
)