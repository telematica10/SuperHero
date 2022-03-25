package com.ajo.superhero.mainModule.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ajo.superhero.Hero
import com.ajo.superhero.HeroResponse
import com.ajo.superhero.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainInteractor {

    val heros: LiveData<MutableList<HeroResponse>> = liveData {
//        val herosLiveData = StoreApplication.database.storeDao().getAllStores()
//        emitSource(herosLiveData.map { stores ->
//            stores.sortedBy { it.name }.toMutableList()
//        })
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HeroResponse::class.java)
        val call = service.getCurrentHeroData(Constants.API_KEY)
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!


                }
            }

            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Log.i("Response", t.message.toString())
            }
        })
    }

}