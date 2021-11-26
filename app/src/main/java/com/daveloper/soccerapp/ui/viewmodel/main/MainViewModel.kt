package com.daveloper.soccerapp.ui.viewmodel.main


import android.content.Context

import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.domain.GetSavedSelectedLeagueUseCase
import com.daveloper.soccerapp.domain.GetTeamsInfoByLeagueUseCase
import com.daveloper.soccerapp.domain.SaveSelectedLeagueUseCase
import com.daveloper.soccerapp.ui.view.team_detail_info.TeamDetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTeamsInfoByLeagueUseCase: GetTeamsInfoByLeagueUseCase,
    private val saveSelectedLeagueUseCase: SaveSelectedLeagueUseCase,
    private val getSavedSelectedLeagueUseCase: GetSavedSelectedLeagueUseCase
): ViewModel() {

    private var leagues = LeagueAPIHelper.getAllLeaguesN()

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility
    
    private val _showInfoMessage = MutableLiveData<Int>()
    val showInfoMessage : LiveData<Int> get() = _showInfoMessage
    
    private val _goToXActivity = MutableLiveData<Class<out AppCompatActivity?>>()
    val goToXActivity : LiveData<Class<out AppCompatActivity?>> get() = _goToXActivity
    
    private val _setSpinnerPosition = MutableLiveData<Int>()
    val setSpinnerPosition : LiveData<Int> get() = _setSpinnerPosition

    private val _spinnerData = MutableLiveData<List<String>>()
    val spinnerData : LiveData<List<String>> get() = _spinnerData

    private val _recyclerViewData = MutableLiveData<List<Team>>()
    val recyclerViewData : LiveData<List<Team>> get() = _recyclerViewData

    private val _refreshRecyclerViewData = MutableLiveData<List<Team>>()
    val refreshRecyclerViewData : LiveData<List<Team>> get() = _refreshRecyclerViewData

    private val _goToXActivityWithData = MutableLiveData<IntentAndTeamData>()
    val goToXActivityWithData : LiveData<IntentAndTeamData> get() = _goToXActivityWithData
    
    fun onCreate(
        context: Context
    ) {
        _progressVisibility.value = true
        _spinnerData.value = leagues
        viewModelScope.launch {
            val league = getSavedSelectedLeagueUseCase.getData(context)
            _setSpinnerPosition.postValue(getSpinnerIndex (league))
            val teamsInfo = getTeamsInfoByLeagueUseCase
                .getInfo(getAPILeagueName(league), context)
            if (!teamsInfo.isNullOrEmpty()) {
                _recyclerViewData.postValue(teamsInfo)
                _progressVisibility.postValue(false)
            }
        }
    }

    private fun getSpinnerIndex(league: String): Int {
        when (league) {
            LeagueAPIHelper.getSpanishLeagueN() -> return 0
            LeagueAPIHelper.getEnglishLeagueN() -> return 1
            LeagueAPIHelper.getItalianLeagueN() -> return 2
            else -> return 0
        }
    }

    fun onRefreshRv(
        context: Context
    ) {
        _progressVisibility.value = true
        viewModelScope.launch {
            val league = getSavedSelectedLeagueUseCase.getData(context)
            val teamsInfo = getTeamsInfoByLeagueUseCase
                .getInfo(getAPILeagueName(league), context)
            if (!teamsInfo.isNullOrEmpty()) {
                _refreshRecyclerViewData.postValue(teamsInfo)
                _progressVisibility.postValue(false)
            }
        }
    }

    fun onTeamClicked(teamSelected: String) {
        _goToXActivityWithData.value =
            IntentAndTeamData(
                TeamDetailActivity::class.java,
                teamSelected
            )
    }

    private fun getAPILeagueName(league: String): String {
        when (league) {
            LeagueAPIHelper.getSpanishLeagueN() -> return LeagueAPIHelper.getSpanishLeague()
            LeagueAPIHelper.getEnglishLeagueN() -> return LeagueAPIHelper.getEnglishLeague()
            LeagueAPIHelper.getItalianLeagueN() -> return LeagueAPIHelper.getItalianLeague()
            else -> return LeagueAPIHelper.getSpanishLeague()
        }
    }

    fun onSpinnerItemChanged(context: Context, newLeague: String) {
        viewModelScope.launch {
            saveSelectedLeagueUseCase.saveInfo(context, newLeague)
            onRefreshRv(context)
        }
    }
}

data class IntentAndTeamData (
    var activity: Class<out AppCompatActivity?>,
    var teamSelected: String
        )