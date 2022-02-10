package one.tunkshif.ankiestrella.data.repository

import one.tunkshif.ankiestrella.data.dao.SchemaDao
import one.tunkshif.ankiestrella.data.model.Schema

class SchemaRepository(private val schemaDao: SchemaDao) {
    fun getAll() =
        schemaDao.getAll()

    suspend fun save(schema: Schema) {
        schemaDao.insert(schema)
    }
}