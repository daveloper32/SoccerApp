package com.daveloper.soccerapp.ui.viewmodel.main.league_teams

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.exception_provider.ExceptionProviderHelper
import com.daveloper.soccerapp.auxiliar.internet_conection.InternetConnection
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.domain.GetSavedSelectedLeagueUseCase
import com.daveloper.soccerapp.domain.GetTeamsInfoByLeagueUseCase
import com.daveloper.soccerapp.domain.SaveSelectedLeagueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class LeagueTeamsViewModel @Inject constructor(
    private val internetConnection: InternetConnection,
    private val getTeamsInfoByLeagueUseCase: GetTeamsInfoByLeagueUseCase,
    private val saveSelectedLeagueUseCase: SaveSelectedLeagueUseCase,
    private val getSavedSelectedLeagueUseCase: GetSavedSelectedLeagueUseCase,
    private val exceptionProviderHelper: ExceptionProviderHelper
): ViewModel(){
    private var leagues = LeagueAPIHelper.getAllLeaguesN()

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility

    private val _showInfoMessage = MutableLiveData<String>()
    val showInfoMessage : LiveData<String> get() = _showInfoMessage

    private val _showInfoMessageFromResource = MutableLiveData<Int>()
    val showInfoMessageFromResource : LiveData<Int> get() = _showInfoMessageFromResource

    private val _setSpinnerPosition = MutableLiveData<Int>()
    val setSpinnerPosition : LiveData<Int> get() = _setSpinnerPosition

    private val _spinnerData = MutableLiveData<List<String>>()
    val spinnerData : LiveData<List<String>> get() = _spinnerData

    private val _recyclerViewData = MutableLiveData<List<Team>>()
    val recyclerViewData : LiveData<List<Team>> get() = _recyclerViewData

    private val _iBReloadTeamsVisibility = MutableLiveData<Boolean>()
    val iBReloadTeamsVisibility : LiveData<Boolean> get() = _iBReloadTeamsVisibility

    private val _refreshRecyclerViewData = MutableLiveData<List<Team>>()
    val refreshRecyclerViewData : LiveData<List<Team>> get() = _refreshRecyclerViewData

    private val _goToXActivityWithData = MutableLiveData<String>()
    val goToXActivityWithData : LiveData<String> get() = _goToXActivityWithData

    fun onCreate() {
        _goToXActivityWithData.value = ""
        _progressVisibility.value = true
        _spinnerData.value = leagues
        viewModelScope.launch {
            val league = getSavedSelectedLeagueUseCase.getData()
            _setSpinnerPosition.postValue(getSpinnerIndex (league))
            getDataToFillRecyclerView (league)
        }
    }

    fun onRefreshRv() {
        _progressVisibility.value = true
        viewModelScope.launch {
            val league = getSavedSelectedLeagueUseCase.getData()
            getDataToFillRecyclerView (league)
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    private suspend fun getDataToFillRecyclerView(
        league: String
    ) {
        try {
            val internetConnectionState = internetConnection.internetIsConnected()
            val teamsInfo = getTeamsInfoByLeagueUseCase
                .getInfo(getAPILeagueName(league), internetConnectionState)
            if (!teamsInfo.isNullOrEmpty()) {
                _recyclerViewData.postValue(teamsInfo)
                _progressVisibility.postValue(false)
            } else {
                _recyclerViewData.postValue(emptyList())
                _showInfoMessageFromResource.postValue(R.string.iM_main_failGetTeams)
                _progressVisibility.postValue(false)
                _iBReloadTeamsVisibility.value = true
            }
        } catch (e: Exception){
            _showInfoMessage.postValue(exceptionProviderHelper.fromStrExceptionToUserMsg(e.toString()))
            _recyclerViewData.postValue(emptyList())
            _progressVisibility.postValue(false)
            _iBReloadTeamsVisibility.value = true
        }

    }

    fun onReloadTeamsClicked() {
        onRefreshRv()
        _iBReloadTeamsVisibility.value = false
    }

    fun onTeamClicked(teamSelected: String) {
        _goToXActivityWithData.value = teamSelected
    }

    private fun getAPILeagueName(league: String): String {
        return when (league) {
            LeagueAPIHelper.getSpanishLeagueN() -> LeagueAPIHelper.getSpanishLeague()
            LeagueAPIHelper.getEnglishLeagueN() -> LeagueAPIHelper.getEnglishLeague()
            LeagueAPIHelper.getItalianLeagueN() -> LeagueAPIHelper.getItalianLeague()
            else -> LeagueAPIHelper.getSpanishLeague()
        }
    }

    private fun getSpinnerIndex(league: String): Int {
        return when (league) {
            LeagueAPIHelper.getSpanishLeagueN() -> 0
            LeagueAPIHelper.getEnglishLeagueN() -> 1
            LeagueAPIHelper.getItalianLeagueN() -> 2
            else -> 0
        }
    }

    fun onSpinnerItemChanged(newLeague: String) {
        viewModelScope.launch {
            try {
                saveSelectedLeagueUseCase.saveInfo(newLeague)
                _iBReloadTeamsVisibility.postValue(false)
                onRefreshRv()
            } catch (e: Exception) {
                _showInfoMessage.postValue(
                    exceptionProviderHelper
                        .fromStrExceptionToUserMsg(
                            e.toString()
                        )
                )
                _iBReloadTeamsVisibility.postValue(false)
                onRefreshRv()
            }
        }
    }
}