package com.rohandakua.rapidopartnerhelperapp.di

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohandakua.rapidopartnerhelperapp.data.offline.datastore.AppDataStore
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.dao.DayOfJobDao
import com.rohandakua.rapidopartnerhelperapp.data.online.firebase.FirebaseDatabaseHandler
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.AuthInterfaceImplementation
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.DayOfJobRepositoryImplementation
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.SettingRepositoryImplementation
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.AuthInterface
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.SettingRepository
import org.koin.dsl.module
import kotlin.math.sin

val koinModule  = module {

    single { AppDataStore(get())} // for single instance of AppDataStore

    single<DatabaseReference> { FirebaseDatabase.getInstance().getReference("users")  } // for the firebase database reference to users

    single<FirebaseDatabaseHandler> { FirebaseDatabaseHandler(get()) } // for single instance of FirebaseDatabaseHandler

    single<AuthInterface> {AuthInterfaceImplementation(get())}

    single<DayOfJobRepository> { DayOfJobRepositoryImplementation(get())}

    single<SettingRepository> {SettingRepositoryImplementation(get())}


}