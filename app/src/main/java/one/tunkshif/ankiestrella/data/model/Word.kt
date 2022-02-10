package one.tunkshif.ankiestrella.data.model

data class Word(
    val query: String,
    val word: String,
    val phonetics: String?,
    val audioUrl: String?,
    val definitions: List<Definition>
)

data class Definition(
    val pos: String?,
    val sense: String,
    val senseTranslation: String?,
    val examples: List<Example>
)

data class Example(
    val example: String,
    val exampleTranslation: String?
)