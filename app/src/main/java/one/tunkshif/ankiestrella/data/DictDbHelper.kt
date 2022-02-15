package one.tunkshif.ankiestrella.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import android.provider.BaseColumns

class DictDbHelper(context: Context, name: String) :
    SQLiteOpenHelper(context, name, null, DATABASE_VERSION) {

    private val databaseFolderPath =
        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.absolutePath

    private val database =
        SQLiteDatabase.openDatabase(
            "$databaseFolderPath/$name.db",
            null,
            SQLiteDatabase.OPEN_READONLY
        )

    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    override fun close() {
        database.close()
        super.close()
    }

    fun query(query: String): List<String> {
        val projection = arrayOf(
            DictContract.DictEntry.COLUMN_NAME_WORD,
            DictContract.DictEntry.COLUMN_NAME_PHONETICS,
            DictContract.DictEntry.COLUMN_NAME_DEFINITIONS
        )
        val selection = "${DictContract.DictEntry.COLUMN_NAME_WORD} = ?"
        val selectionArgs = arrayOf(query)

        val cursor = database.query(
            DictContract.DictEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null, null, null
        )

        val columns = mutableListOf<String>()

        with(cursor) {
            while (moveToNext()) {
                val word =
                    getString(getColumnIndexOrThrow(DictContract.DictEntry.COLUMN_NAME_WORD))
                val phonetics =
                    getString(getColumnIndexOrThrow(DictContract.DictEntry.COLUMN_NAME_PHONETICS))
                val definitions =
                    getString(getColumnIndexOrThrow(DictContract.DictEntry.COLUMN_NAME_DEFINITIONS))
                columns.apply {
                    add(word)
                    add(phonetics)
                    add(definitions)
                }
            }
        }
        return columns
    }

    companion object {
        const val DATABASE_VERSION = 1

        object DictContract {
            object DictEntry : BaseColumns {
                const val TABLE_NAME = "dict"
                const val COLUMN_NAME_WORD = "word"
                const val COLUMN_NAME_PHONETICS = "phonetics"
                const val COLUMN_NAME_DEFINITIONS = "definitions"
            }
        }
    }
}