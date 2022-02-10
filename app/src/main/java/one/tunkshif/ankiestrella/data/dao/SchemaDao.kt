package one.tunkshif.ankiestrella.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import one.tunkshif.ankiestrella.data.model.Schema

@Dao
interface SchemaDao {
    @Query("SELECT * FROM schemas")
    fun getAll(): Flow<List<Schema>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schema: Schema)
}