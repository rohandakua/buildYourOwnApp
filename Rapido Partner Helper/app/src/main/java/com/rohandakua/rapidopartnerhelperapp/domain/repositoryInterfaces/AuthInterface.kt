package com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import kotlinx.coroutines.tasks.await

interface AuthInterface {
    suspend fun login(rapidoPartnerId: Int, password: String): Boolean
    suspend fun getUserProfile(rapido_partner_id: Int): RapidoPartner?
}