package one.tunkshif.ankiestrella.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schemas")
data class Schema(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val source: String = "",
    val deck: String = "",
    val model: String = "",
    val fieldMapping: Map<String, String> = emptyMap()
)