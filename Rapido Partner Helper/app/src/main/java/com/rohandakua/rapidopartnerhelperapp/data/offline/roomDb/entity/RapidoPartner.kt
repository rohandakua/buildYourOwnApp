package com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rohandakua.rapidopartnerhelperapp.domain.model.RapidoPartnerUiModel

@Entity(tableName = "rapido_partners")
data class RapidoPartner(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String? = "",
    @ColumnInfo(name = "rating") val rating: Double? = 0.0,
    @ColumnInfo(name = "imageUrl") val imageUrl: String? = "",
    @ColumnInfo(name = "earning") val earning: Double? = 0.0,
    @ColumnInfo(name = "gender") val gender: String? = "m",
    @ColumnInfo(name = "age") val age: Int? = 20
) {
    companion object {
        fun fromUiModel(uiModel: RapidoPartnerUiModel): RapidoPartner {
            return RapidoPartner(
                id = uiModel.id,
                name = uiModel.name,
                rating = uiModel.rating,
                imageUrl = uiModel.imageUrl,
                earning = uiModel.earning,
                gender = uiModel.gender?.toString(),
                age = uiModel.age
            )
        }

        fun toUiModel(entity: RapidoPartner): RapidoPartnerUiModel {
            return RapidoPartnerUiModel(
                id = entity.id,
                name = entity.name,
                rating = entity.rating,
                imageUrl = entity.imageUrl,
                earning = entity.earning,
                gender = entity.gender?.firstOrNull(),
                age = entity.age
            )
        }
    }
}
