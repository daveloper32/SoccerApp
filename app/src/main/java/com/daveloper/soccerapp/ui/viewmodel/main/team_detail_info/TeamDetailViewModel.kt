package com.daveloper.soccerapp.ui.viewmodel.main.team_detail_info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.internet_conection.InternetConnection
import com.daveloper.soccerapp.auxiliar.resource_provider.ResourceProviderHelper
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.domain.GetTeamInfoFromLocalDBUseCase
import com.daveloper.soccerapp.domain.GetXNextTeamEventsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val resourceProviderHelper: ResourceProviderHelper,
    private val internetConnection: InternetConnection,
    private val getXNextTeamEventsInfoUseCase: GetXNextTeamEventsInfoUseCase,
    private val getTeamInfoFromLocalDBUseCase: GetTeamInfoFromLocalDBUseCase
): ViewModel() {

    private lateinit var teamInfo: Team

    private lateinit var teamName: String

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility

    private val _progressEventsVisibility = MutableLiveData<Boolean>()
    val progressEventsVisibility : LiveData<Boolean> get() = _progressEventsVisibility

    private val _showInfoMessage = MutableLiveData<Int>()
    val showInfoMessage : LiveData<Int> get() = _showInfoMessage

    private val _showStrInfoMessage = MutableLiveData<String>()
    val showStrInfoMessage : LiveData<String> get() = _showStrInfoMessage

    private val _goToXActivity = MutableLiveData<Boolean>()
    val goToXActivity : LiveData<Boolean> get() = _goToXActivity

    private val _recyclerViewData = MutableLiveData<List<Event>>()
    val recyclerViewData : LiveData<List<Event>> get() = _recyclerViewData

    private val _iBReloadEventsVisibility = MutableLiveData<Boolean>()
    val iBReloadEventsVisibility : LiveData<Boolean> get() = _iBReloadEventsVisibility
    
    private val _setTextTeamName = MutableLiveData<String>()
    val setTextTeamName : LiveData<String> get() = _setTextTeamName
    
    private val _setTextTeamDescription = MutableLiveData<String>()
    val setTextTeamDescription : LiveData<String> get() = _setTextTeamDescription
    
    private val _setTextFoundationYear = MutableLiveData<String>()
    val setTextFoundationYear : LiveData<String> get() = _setTextFoundationYear
    
    private val _setImageTeamBadge = MutableLiveData<String>()
    val setImageTeamBadge : LiveData<String> get() = _setImageTeamBadge
    
    private val _setImageTeamJersey = MutableLiveData<String>()
    val setImageTeamJersey : LiveData<String> get() = _setImageTeamJersey
    
    private val _setTextWebpage = MutableLiveData<String>()
    val setTextWebpage : LiveData<String> get() = _setTextWebpage
    
    private val _linkToWebpage = MutableLiveData<Intent>()
    val linkToWebpage : LiveData<Intent> get() = _linkToWebpage

    private val _linkToFacebookWebpage = MutableLiveData<Intent>()
    val linkToFacebookWebpage : LiveData<Intent> get() = _linkToFacebookWebpage

    private val _linkToInstagramWebpage = MutableLiveData<Intent>()
    val linkToInstagramWebpage : LiveData<Intent> get() = _linkToInstagramWebpage

    private val _linkToTwitterWebpage = MutableLiveData<Intent>()
    val linkToTwitterWebpage : LiveData<Intent> get() = _linkToTwitterWebpage

    private val _linkToYoutubeWebpage = MutableLiveData<Intent>()
    val linkToYoutubeWebpage : LiveData<Intent> get() = _linkToYoutubeWebpage

    private val _tVSocialNetworkVisibility = MutableLiveData<Boolean>()
    val tVSocialNetworkVisibility : LiveData<Boolean> get() = _tVSocialNetworkVisibility

    private val _iBWebpageVisibility = MutableLiveData<Boolean>()
    val iBWebpageVisibility : LiveData<Boolean> get() = _iBWebpageVisibility

    private val _iBFacebookVisibility = MutableLiveData<Boolean>()
    val iBFacebookVisibility : LiveData<Boolean> get() = _iBFacebookVisibility

    private val _iBInstagramVisibility = MutableLiveData<Boolean>()
    val iBInstagramVisibility : LiveData<Boolean> get() = _iBInstagramVisibility

    private val _iBTwitterVisibility = MutableLiveData<Boolean>()
    val iBTwitterVisibility : LiveData<Boolean> get() = _iBTwitterVisibility

    private val _iBYoutubeVisibility = MutableLiveData<Boolean>()
    val iBYoutubeVisibility : LiveData<Boolean> get() = _iBYoutubeVisibility

    fun onCreate(
        teamName: String?
    ) {
        _progressVisibility.value = true
        if (teamName != null) {
            if (!teamName.isEmpty()) {
                fillInTeamInfo(teamName)
            } else {
                _goToXActivity.value = true
                _showInfoMessage.value = R.string.iM_team_det_initError
                _progressVisibility.value = false
            }
        } else {
            _goToXActivity.value = true
            _showInfoMessage.value = R.string.iM_team_det_initError
            _progressVisibility.value = false
        }
    }

    private fun fillInTeamInfo(
        teamName: String
    ) {
        viewModelScope.launch {
            teamInfo = getTeamInfoFromLocalDBUseCase.getInfo(teamName)
            // Fill the Infromation Fields of the Team
            _setTextTeamName.postValue(teamInfo.name)
            if (!teamInfo.teamDescription.isNullOrEmpty()) {
                _setTextTeamDescription.postValue(teamInfo.teamDescription!!)
            } else {
                _setTextTeamDescription.postValue(
                    resourceProviderHelper.getStringResource(R.string.msg_team_det_noInfo)
                )
            }
            if (!teamInfo.foundationYear.isNullOrEmpty()) {
                _setTextFoundationYear.postValue("  ${teamInfo.foundationYear!!}")
            } else {
                _setTextFoundationYear.postValue(
                    resourceProviderHelper.getStringResource(R.string.msg_team_det_noInfo)
                )
            }
            if (!teamInfo.teamBadge.isNullOrEmpty()) {
                _setImageTeamBadge.postValue(teamInfo.teamBadge!!)
            }
            if (!teamInfo.teamJersey.isNullOrEmpty()) {
                _setImageTeamJersey.postValue(teamInfo.teamJersey!!)
            }
            if (!teamInfo.websiteLink.isNullOrEmpty()) {
                _setTextWebpage.postValue(teamInfo.websiteLink!!)
            } else {
                _setTextWebpage.postValue(
                    resourceProviderHelper.getStringResource(R.string.msg_team_det_noInfo)
                )
                _iBWebpageVisibility.postValue(false)

            }
            if (teamInfo.facebookLink.isNullOrEmpty() &&
                    teamInfo.instagramLink.isNullOrEmpty() &&
                    teamInfo.twitterLink.isNullOrEmpty() &&
                    teamInfo.youtubeLink.isNullOrEmpty()) {
                _tVSocialNetworkVisibility.postValue(false)
                _iBFacebookVisibility.postValue(false)
                _iBInstagramVisibility.postValue(false)
                _iBTwitterVisibility.postValue(false)
                _iBYoutubeVisibility.postValue(false)
            }

            if (!teamInfo.facebookLink.isNullOrEmpty()){
                _iBFacebookVisibility.postValue(true)
            } else {
                _iBFacebookVisibility.postValue(false)
            }
            if (!teamInfo.instagramLink.isNullOrEmpty()){
                _iBInstagramVisibility.postValue(true)
            } else {
                _iBInstagramVisibility.postValue(false)
            }
            if (!teamInfo.twitterLink.isNullOrEmpty()){
                _iBTwitterVisibility.postValue(true)
            } else {
                _iBTwitterVisibility.postValue(false)
            }
            if (!teamInfo.youtubeLink.isNullOrEmpty()){
                _iBYoutubeVisibility.postValue(true)
            } else {
                _iBYoutubeVisibility.postValue(false)
            }
            _progressVisibility.postValue(false)

            getDataForRecyclerView (teamName)
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    private fun getDataForRecyclerView(
        teamName: String
    ) {
        _progressEventsVisibility.value = true
        viewModelScope.launch {
            val internetConnectionState = internetConnection.internetIsConnected()
            val eventsInfo = teamInfo.league?.let {
                getAPILeagueID(
                    it
                )
            }?.let {
                getXNextTeamEventsInfoUseCase
                    .getInfo(teamName = teamName,
                        idLeague = it,
                        internetConnection = internetConnectionState
                    )
            }
            if (!eventsInfo.isNullOrEmpty()) {
                _recyclerViewData.postValue(eventsInfo)
                _progressEventsVisibility.postValue(false)
            } else {
                _showInfoMessage.postValue(R.string.iM_team_det_failGetEvents)
                _progressEventsVisibility.postValue(false)
                _iBReloadEventsVisibility.postValue(true)
            }
        }
    }

    fun onBackClicked() {
        _goToXActivity.value = true
    }

    fun onReloadEventsClicked(
    ) {
        getDataForRecyclerView(teamName)
        _iBReloadEventsVisibility.value = false
    }

    fun onWebpageTeamClicked() {
        if (!teamInfo.websiteLink.isNullOrEmpty()) {
            _linkToWebpage.postValue(Intent(Intent.ACTION_VIEW, Uri.parse("https://${teamInfo.websiteLink!!}")))
        }
    }

    fun onFacebookWebpageTeamClicked() {
        if (!teamInfo.facebookLink.isNullOrEmpty()){
            _linkToFacebookWebpage.postValue(Intent(Intent.ACTION_VIEW, Uri.parse("https://${teamInfo.facebookLink!!}")))
        }
    }

    fun onInstagramWebpageTeamClicked() {
        if (!teamInfo.instagramLink.isNullOrEmpty()){
            _linkToInstagramWebpage.postValue(Intent(Intent.ACTION_VIEW, Uri.parse("https://${teamInfo.instagramLink!!}")))
        }
    }

    fun onTwitterWebpageTeamClicked() {
        if (!teamInfo.twitterLink.isNullOrEmpty()){
            _linkToTwitterWebpage.postValue(Intent(Intent.ACTION_VIEW, Uri.parse("https://${teamInfo.twitterLink!!}")))
        }
    }

    fun onYoutubeWebpageTeamClicked() {
        if (!teamInfo.youtubeLink.isNullOrEmpty()){
            _linkToYoutubeWebpage.postValue(Intent(Intent.ACTION_VIEW, Uri.parse("https://${teamInfo.youtubeLink!!}")))
        }
    }

    private fun getAPILeagueID(league: String): Int {
        return when (league) {
            LeagueAPIHelper.getSpanishLeagueN() -> LeagueAPIHelper.getSpanishLeagueID()
            LeagueAPIHelper.getEnglishLeagueN() -> LeagueAPIHelper.getEmglishLeagueID()
            LeagueAPIHelper.getItalianLeagueN() -> LeagueAPIHelper.getItalianLeagueID()
            else -> LeagueAPIHelper.getSpanishLeagueID()
        }
    }
}