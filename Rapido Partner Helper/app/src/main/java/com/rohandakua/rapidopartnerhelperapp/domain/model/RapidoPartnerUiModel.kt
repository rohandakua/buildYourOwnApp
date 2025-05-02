package com.rohandakua.rapidopartnerhelperapp.domain.model

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner

data class RapidoPartnerUiModel(
    val id: Long,
    val name: String?,
    val rating: Double?,
    val imageUrl: String?,
    val earning: Double?,
    val gender: Char?,
    val age: Int?
) {


    companion object {
        fun fromEntity(entity: RapidoPartner): RapidoPartnerUiModel {
            return RapidoPartnerUiModel(
                id = entity.id,
                name = entity.name,
                rating = entity.rating,
                imageUrl = entity.imageUrl,
                earning = entity.earning,
                gender = entity.gender!!.toCharArray()[0],
                age = entity.age
            )
        }

        fun toEntity(uiModel: RapidoPartnerUiModel): RapidoPartner {
            return RapidoPartner(
                id = uiModel.id,
                name = uiModel.name,
                rating = uiModel.rating,
                imageUrl = uiModel.imageUrl,
                earning = uiModel.earning,
                gender = uiModel.gender.toString(),
                age = uiModel.age
            )
        }
    }
}
