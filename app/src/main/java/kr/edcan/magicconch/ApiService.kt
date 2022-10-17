package kr.edcan.magicconch

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    suspend fun getAnswer() : Response<Answer>
}