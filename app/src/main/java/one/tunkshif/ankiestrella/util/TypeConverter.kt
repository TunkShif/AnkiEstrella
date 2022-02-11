package one.tunkshif.ankiestrella.util

import androidx.room.TypeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object TypeConverter {
    private val mapper = ObjectMapper().registerKotlinModule()

    @TypeConverter
    fun stringToMap(value: String): Map<String, String> = mapper.readValue(value)

    @TypeConverter
    fun mapToString(value: Map<String, String>): String = mapper.writeValueAsString(value)
}