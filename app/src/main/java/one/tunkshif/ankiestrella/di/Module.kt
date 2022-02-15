package one.tunkshif.ankiestrella.di

import androidx.room.Room
import one.tunkshif.ankiestrella.AnkiEstrella
import one.tunkshif.ankiestrella.data.AppDatabase
import one.tunkshif.ankiestrella.data.repository.SchemaRepository
import one.tunkshif.ankiestrella.data.service.SpanishDictService
import one.tunkshif.ankiestrella.data.service.YoudaoCollinsService
import one.tunkshif.ankiestrella.ui.home.HomeViewModel
import one.tunkshif.ankiestrella.ui.editor.EditorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val appModule = module {
    viewModel { EditorViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(AnkiEstrella.context, AppDatabase::class.java, "anki_estrella.db")
            .build()
    }

    single { get<AppDatabase>().schemaDao() }

    single { SchemaRepository(get()) }
}

val serviceModule = module {
    fun provideHttpClient() =
        Retrofit.Builder()
            .baseUrl("https://dictlet.herokuapp.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    single { provideHttpClient() }
    single { get<Retrofit>().create(SpanishDictService::class.java) }
    single { get<Retrofit>().create(YoudaoCollinsService::class.java) }
}