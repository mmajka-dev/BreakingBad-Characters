package com.example.breakingbadcharacters.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadcharacters.models.CharacterItem
import com.example.breakingbadcharacters.rest.ApiClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var characters = MutableLiveData<ArrayList<CharacterItem>>()


    fun getCharacters(view: View){
        val callGet = ApiClient.instance.getCharacters()
        val _charactersTemp = ArrayList<CharacterItem>()
        callGet.enqueue(object : Callback<List<CharacterItem>>{
            override fun onFailure(call: Call<List<CharacterItem>>, t: Throwable) {
                Log.e("Retrofit error: ", "${t.message}")
            }

            override fun onResponse(
                call: Call<List<CharacterItem>>,
                response: Response<List<CharacterItem>>
            ) {
                if (response?.isSuccessful){
                   response.body()!!.forEach {
                        _charactersTemp.add(it)
                   }
                }else{
                    Snackbar.make(view, "Something went wrong.", Snackbar.LENGTH_SHORT).show()
                }
                characters.value = _charactersTemp
            }
        })
    }

}