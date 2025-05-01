package com.rohandakua.rapidopartnerhelperapp.data.online.firebase

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.entity.RapidoPartner
import kotlinx.coroutines.tasks.await
import kotlin.reflect.KClass

class FirebaseDatabaseHandler(private val firebaseReference: DatabaseReference) {
    val TAG = "FirebaseDatabaseHandler"


    /**
     * Here we can make the password in hash valyue to increase the privacy but this can be later implemented
     */
    suspend fun login(rapidoPartnerId: Int, password: String): Boolean {
        return try {
            val snapshot = firebaseReference.child(rapidoPartnerId.toString())
                .child("password").get().await()

            val storedPassword = snapshot.getValue(String::class.java)
            storedPassword == password
        } catch (e: Exception) {
            Log.e(TAG,"login error: "+  e.message.toString())
            false
        }
    }

    suspend fun getUserProfile(rapido_partner_id: Int): RapidoPartner? {
        return try {
            val snapshot: DataSnapshot =
                firebaseReference.child(rapido_partner_id.toString())
                    .child("profile")
                    .get().await()
            snapshot.getValue(RapidoPartner::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "getUserProfile error: " + e.message.toString())
            null
        }
    }
}