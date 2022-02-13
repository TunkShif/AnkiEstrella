package one.tunkshif.ankiestrella.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import one.tunkshif.ankiestrella.data.model.Schema

@Dao
interface SchemaDao {
    @Query("SELECT * FROM schemas")
    fun getAll(): Flow<List<Schema>>

    @Query("SELECT * FROM schemas WHERE name = :name")
    suspend fun getOne(name: String): Schema?

    @Query("SELECT * FROM schemas WHERE id = :id")
    suspend fun getOne(id: Int): Schema?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schema: Schema)

    @Update
    suspend fun update(schema: Schema)

    @Delete
    suspend fun delete(schema: Schema)
}