package one.tunkshif.ankiestrella.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.ichi2.anki.api.AddContentApi
import com.ichi2.anki.api.AddContentApi.READ_WRITE_PERMISSION
import one.tunkshif.ankiestrella.AnkiEstrella

typealias DeckId = Long
typealias ModelId = Long

@SuppressLint("StaticFieldLeak")
object AnkiDroidHelper {

    private val context: Context = AnkiEstrella.context
    private val api: AddContentApi = AddContentApi(context)

    fun isApiAvailable(): Boolean =
        AddContentApi.getAnkiDroidPackageName(context) != null

    fun shouldRequestPermission(): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            READ_WRITE_PERMISSION
        ) != PackageManager.PERMISSION_GRANTED

    fun getAllDecks(): List<String> =
        api.deckList.values.toList()

    fun getAllModels(): List<String> =
        api.modelList.values.toList()

    fun getDeckIdByName(name: String): DeckId? =
        api.deckList.filterValues { it == name }.keys.firstOrNull()

    fun getModelIdByName(name: String): ModelId? =
        api.modelList.filterValues { it == name }.keys.firstOrNull()

    fun getModelFields(name: String): List<String>? =
        getModelIdByName(name)?.let { api.getFieldList(it) }?.toList()
}