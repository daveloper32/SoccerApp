package com.daveloper.soccerapp.data.local_database.shared_prefs

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.daveloper.soccerapp.core.LeagueAPIHelper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SharedPrefsTest {

    private lateinit var userLocalData: UserLocalData

    @Before
    fun setup() {
        userLocalData = UserLocalData(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun setAndGetDifferentUserLocalData () = runBlockingTest {
        userLocalData.setUserLocalData(
            LeagueAPIHelper.getItalianLeagueN()
        )
        val data = userLocalData.getUserLocalData()

        assertThat(data).isEqualTo(LeagueAPIHelper.getItalianLeagueN())
        assertThat(data).doesNotMatch(LeagueAPIHelper.getSpanishLeagueN())
    }
}