package net.chigita.illuminat.api

import net.chigita.illuminat.api.request.IdRequest
import net.chigita.illuminat.api.request.PatternRequest
import net.chigita.illuminat.api.response.IdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IlluminatService {
  @GET("/get_playing_pattern")
  suspend fun getCurrentPattern(): IdResponse

  @POST("/play_pattern")
  suspend fun playPattern(@Body id: IdRequest)

  @POST("/register_pattern")
  suspend fun registerPattern(@Body pattern: PatternRequest): IdResponse
}