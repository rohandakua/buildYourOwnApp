package com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation

import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import com.rohandakua.rapidopartnerhelperapp.data.online.firebase.FirebaseDatabaseHandler
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.AuthInterface

class AuthInterfaceImplementation (
    private val firebaseDatabaseHandler: FirebaseDatabaseHandler
) : AuthInterface {
    override suspend fun login(rapidoPartnerId: Int, password: String): Boolean {
        if(rapidoPartnerId.toString().length != 10){
            throw IllegalArgumentException("Rapido Partner ID must be 10 digits")
        }
        if(password.length<8){
            throw IllegalArgumentException("Password must be at least 8 characters")
        }
        return firebaseDatabaseHandler.login(rapidoPartnerId, password)
    }

    override suspend fun getUserProfile(rapido_partner_id: Int): RapidoPartner? {
        if(rapido_partner_id.toString().length != 10){
            throw IllegalArgumentException("Rapido Partner ID must be 10 digits")
        }
        return firebaseDatabaseHandler.getUserProfile(rapido_partner_id)
    }
}