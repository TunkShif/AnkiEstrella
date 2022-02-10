package one.tunkshif.ankiestrella.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToMap(value: String): Map<String, String> =
        gson.fromJson(value, object : TypeToken<Map<String, String>>() {}.type)

    @TypeConverter
    fun mapToString(value: Map<String, String>): String =
        gson.toJson(value)
}