package com.ajo.superhero.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajo.superhero.Hero
import com.ajo.superhero.mainModule.model.MainInteractor
import com.ajo.superhero.utils.Constants
import com.ajo.superhero.utils.HerosException
import com.ajo.superhero.utils.TypeError
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var interactor: MainInteractor = MainInteractor()

    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val heros = interactor.heros

    fun getHeros(): LiveData<MutableList<Hero>> {
        return heros
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun isShowProgress(): LiveData<Boolean> {
        return showProgress
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            showProgress.value = Constants.SHOW

            try {
                block()
            } catch (e: HerosException){
                typeError.value = e.typeError
            } finally {
                showProgress.value = Constants.HIDE
            }
        }
    }
}