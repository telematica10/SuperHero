package com.ajo.superhero.detailHeroModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajo.superhero.Hero
import com.ajo.superhero.detailHeroModule.model.DetailHeroInteractor
import com.ajo.superhero.utils.TypeError

class DetailHeroViewModel : ViewModel() {
    private var heroId: Long = 0
    private val showFab = MutableLiveData<Boolean>()
    private val interactor: DetailHeroInteractor = DetailHeroInteractor()


    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    fun setTypeError(typeError: TypeError){
        this.typeError.value = typeError
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun setHeroSelected(hero: Hero){
        heroId = hero.id.toLong()
    }

    fun getHeroSelected(): LiveData<Hero> {
        return interactor.getHeroById(heroId)
    }

    fun setShowFab(isVisible: Boolean){
        showFab.value = isVisible
    }

    fun getShowFab(): LiveData<Boolean> {
        return showFab
    }
}