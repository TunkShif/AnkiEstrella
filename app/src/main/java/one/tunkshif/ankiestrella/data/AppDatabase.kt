package one.tunkshif.ankiestrella.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.tunkshif.ankiestrella.data.dao.SchemaDao
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.util.TypeConverter

@Database(entities = [Schema::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun schemaDao(): SchemaDao
}