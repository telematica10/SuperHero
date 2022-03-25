package com.ajo.superhero.detailHeroModule.model

import androidx.lifecycle.LiveData
import com.ajo.superhero.Hero

class DetailHeroInteractor {

    fun getHeroById(id: Long): LiveData<Hero> {
        return LiveData<Hero>()// StoreApplication.database.storeDao().getStoreById(id)
    }
}