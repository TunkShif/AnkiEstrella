package one.tunkshif.ankiestrella.data.service

import one.tunkshif.ankiestrella.data.model.Resource
import one.tunkshif.ankiestrella.data.model.Word
import retrofit2.http.GET
import retrofit2.http.Path

interface YoudaoCollinsService {
    @GET("youdao-collins/query/{query}")
    suspend fun getWordQuery(@Path("query") query: String): Resource<Word>
}