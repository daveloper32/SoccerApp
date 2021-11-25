package com.daveloper.soccerapp.ui.viewmodel.team_detail_info

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.ui.view.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(

): ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility

    private val _progressEventsVisibility = MutableLiveData<Boolean>()
    val progressEventsVisibility : LiveData<Boolean> get() = _progressEventsVisibility

    private val _showInfoMessage = MutableLiveData<Int>()
    val showInfoMessage : LiveData<Int> get() = _showInfoMessage

    private val _showStrInfoMessage = MutableLiveData<String>()
    val showStrInfoMessage : LiveData<String> get() = _showStrInfoMessage

    private val _goToXActivity = MutableLiveData<Class<out AppCompatActivity?>>()
    val goToXActivity : LiveData<Class<out AppCompatActivity?>> get() = _goToXActivity

    private val _recyclerViewData = MutableLiveData<List<Event>>()
    val recyclerViewData : LiveData<List<Event>> get() = _recyclerViewData

    fun onCreate(intent: Intent?) {
        _progressVisibility.value = true
        val teamName = getTeamName(intent)
        if (teamName != null) {
            if (teamName.isEmpty()) {
                _goToXActivity.value = MainActivity::class.java
                _showInfoMessage.value = R.string.iM_team_det_initError
            } else {
                _showStrInfoMessage.value = "$teamName selected!"
            }
        } else {
            _showStrInfoMessage.value = "$teamName selected!"
        }
        //_progressEventsVisibility.value = true
    }

    fun getTeamName (
        intent: Intent?
    ) : String? {
        if (intent != null) {
            return intent.getStringExtra("data")
        } else {
            return ""
        }
    }

    fun onBackClicked() {
        _goToXActivity.value = MainActivity::class.java
    }

    fun onWebpageTeamClicked() {
        TODO("Not yet implemented")
    }

    fun onFacebookWebpageTeamClicked() {
        TODO("Not yet implemented")
    }

    fun onInstagramWebpageTeamClicked() {
        TODO("Not yet implemented")
    }

    fun onTwitterWebpageTeamClicked() {
        TODO("Not yet implemented")
    }

    fun onYoutubeWebpageTeamClicked() {
        TODO("Not yet implemented")
    }




}