package one.tunkshif.ankiestrella.di

import androidx.room.Room
import one.tunkshif.ankiestrella.AnkiEstrella
import one.tunkshif.ankiestrella.data.AppDatabase
import one.tunkshif.ankiestrella.data.repository.SchemaRepository
import one.tunkshif.ankiestrella.data.service.SpanishDictService
import one.tunkshif.ankiestrella.data.service.YoudaoCollinsService
import one.tunkshif.ankiestrella.ui.schema.EditSchemaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { EditSchemaViewModel(get()) }
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
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    single { provideHttpClient() }
    single { get<Retrofit>().create(SpanishDictService::class.java) }
    single { get<Retrofit>().create(YoudaoCollinsService::class.java) }
}