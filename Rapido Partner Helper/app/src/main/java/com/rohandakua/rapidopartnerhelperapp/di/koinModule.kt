package com.rohandakua.rapidopartnerhelperapp.di

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohandakua.rapidopartnerhelperapp.data.offline.datastore.AppDataStore
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.dao.DayOfJobDao
import com.rohandakua.rapidopartnerhelperapp.data.offline.roomDb.db.AppDatabase
import com.rohandakua.rapidopartnerhelperapp.data.online.firebase.FirebaseDatabaseHandler
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.AuthInterfaceImplementation
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.DayOfJobRepositoryImplementation
import com.rohandakua.rapidopartnerhelperapp.data.repositoryImplementation.SettingRepositoryImplementation
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.AuthInterface
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.DayOfJobRepository
import com.rohandakua.rapidopartnerhelperapp.domain.repositoryInterfaces.SettingRepository
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.AuthUseCase
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.JobManagementUseCase
import com.rohandakua.rapidopartnerhelperapp.domain.usecase.SettingsUseCase
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.HomeScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.LoginScreenViewModel
import com.rohandakua.rapidopartnerhelperapp.presentation.viewModel.SettingScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val koinModule  = module {

    single { AppDataStore(get())} // for single instance of AppDataStore

    single<DatabaseReference> { FirebaseDatabase.getInstance().getReference("users")  } // for the firebase database reference to users

    single<FirebaseDatabaseHandler> { FirebaseDatabaseHandler(get()) } // for single instance of FirebaseDatabaseHandler

    single<AuthInterface> {AuthInterfaceImplementation(get())}

    single {
        Room.databaseBuilder(
            get(), // context
            AppDatabase::class.java,
            "rapido_db"
        ).build()
    }

    single<DayOfJobDao> { get<AppDatabase>().dayOfJobDao() }

    single<DayOfJobRepository> { DayOfJobRepositoryImplementation(get())}

    single<SettingRepository> {SettingRepositoryImplementation(get())}

    single { AuthUseCase(get()) }
    single { JobManagementUseCase(get()) }
    single { SettingsUseCase(get()) }

    viewModel { (partnerId: Long) ->
        HomeScreenViewModel(
            jobManagementUseCase = get(),  partnerId = partnerId
        )
    }

    viewModel { LoginScreenViewModel(get(), get()) }

    viewModel { SettingScreenViewModel(get()) }
}