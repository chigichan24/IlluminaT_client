package net.chigita.illuminat.api

import net.chigita.illuminat.api.response.IdResponse
import retrofit2.http.GET

interface IlluminatService {
  @GET("/get_playing_pattern")
  suspend fun getCurrentPattern(): IdResponse
}