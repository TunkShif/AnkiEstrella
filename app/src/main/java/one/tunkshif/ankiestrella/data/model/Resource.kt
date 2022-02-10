package one.tunkshif.ankiestrella.data.model

data class Resource<out T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val result: T?
)
