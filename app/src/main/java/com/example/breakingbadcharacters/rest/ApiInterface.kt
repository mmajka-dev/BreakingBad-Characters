package com.example.breakingbadcharacters.rest

import com.example.breakingbadcharacters.models.CharacterItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("characters")
    fun getCharacters(): Call<List<CharacterItem>>

}