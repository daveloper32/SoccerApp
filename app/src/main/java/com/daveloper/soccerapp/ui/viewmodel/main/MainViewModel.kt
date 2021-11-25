package com.daveloper.soccerapp.ui.viewmodel.main


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.data.model.repository.OnResultSearchTeamsInfoByLeague
import com.daveloper.soccerapp.data.model.repository.SoccerLeagueDataRepository
import com.daveloper.soccerapp.domain.GetTeamsInfoByLeagueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTeamsInfoByLeagueUseCase: GetTeamsInfoByLeagueUseCase
): ViewModel() {
    
    private val spanishLeague = "Spanish_La_Liga"
    private val englishLeague = "English_Premier_League"
    private val italianLeague = "Italian_Serie_A"
    
    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility
    
    private val _showInfoMessage = MutableLiveData<Int>()
    val showInfoMessage : LiveData<Int> get() = _showInfoMessage
    
    private val _goToXActivity = MutableLiveData<Class<out AppCompatActivity?>>()
    val goToXActivity : LiveData<Class<out AppCompatActivity?>> get() = _goToXActivity

    private val _recyclerViewData = MutableLiveData<List<Team>>()
    val recyclerViewData : LiveData<List<Team>> get() = _recyclerViewData
    
    fun onCreate() {
        _progressVisibility.value = true
        viewModelScope.launch {
            val teamsInfo = getTeamsInfoByLeagueUseCase
                .getInfo("Spanish_La_Liga")
            if (!teamsInfo.isNullOrEmpty()) {
                _recyclerViewData.postValue(teamsInfo)
                _progressVisibility.postValue(false)
            }
        }
    }
}