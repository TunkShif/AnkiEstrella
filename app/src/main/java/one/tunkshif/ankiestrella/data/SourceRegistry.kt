package one.tunkshif.ankiestrella.data

import one.tunkshif.ankiestrella.api.DictSource

object SourceRegistry {
    private val sources: MutableMap<String, DictSource> = mutableMapOf()

    val count: Int
        get() = sources.size

    fun register(source: DictSource) {
        sources[source.id] = source
    }

    fun all(): List<DictSource> =
        sources.values.toList()

    fun getById(id: String): DictSource? =
        sources[id]

    fun getByName(name: String): DictSource? =
        sources.values.firstOrNull { it.name == name }
}