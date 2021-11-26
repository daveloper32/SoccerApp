package com.daveloper.soccerapp.data.local_database.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.daveloper.soccerapp.data.local_database.room.database.RoomTeamsDatabase
import com.daveloper.soccerapp.data.model.entity.Team
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TeamDaoTest {
    private lateinit var dB: RoomTeamsDatabase
    private lateinit var dao: TeamDao

    @Before
    fun setup() {
        dB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomTeamsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = dB.teamDao()
    }

    @After
    fun teardown () {
        dB.close()
    }

    @Test
    fun insertATeam () = runBlockingTest {
        val team = Team (
            "Barcelona",
            "Camp Nou",
            "Futbol Club Barcelona, also known as Barcelona and familiarly as Barça, is a professional football club, based in Barcelona, Catalonia, Spain.\r\n\r\nFounded in 1899 by a group of Swiss, English and Catalan footballers led by Joan Gamper, the club has become a symbol of Catalan culture and Catalanism, hence the motto \"Més que un club\" (More than a club). Unlike many other football clubs, the supporters own and operate Barcelona. It is the second most valuable sports team in the world, worth $3.2 billion, and the world's second-richest football club in terms of revenue, with an annual turnover of $613 million. The official Barcelona anthem is the \"Cant del Barça\", written by Jaume Picas and Josep Maria Espinàs.\r\n\r\nBarcelona is the joint most successful club in Spain, in terms of overall official titles won (80). It has won 22 La Liga, 26 Copa del Rey, 11 Supercopa de España, 2 Copa Eva Duarte and 2 Copa de la Liga trophies, as well as being the record holder for the latter four competitions. In international club football, Barcelona has won four UEFA Champions League, a record four UEFA Cup Winners' Cup, four UEFA Super Cup, a record three Inter-Cities Fairs Cup and a record two FIFA Club World Cup trophies. Barcelona was ranked first in the IFFHS Club World Ranking for 1997, 2009, 2011 and 2012 and currently occupies the second position on the UEFA club rankings. The club has a long-standing rivalry with Real Madrid; matches between the two teams are referred to as \"El Clásico\".\r\n\r\nBarcelona is one of the most supported teams in the world, and has the largest social media following in the world among sports teams. Barcelona's players have won a record number of Ballon d'Or awards (10), as well as a record number of FIFA World Player of the Year awards (7). In 2010, the club created history when three players who came through its youth academy (Messi, Iniesta & Xavi) were chosen as the three best players in the world, having bagged the top spots at the FIFA Ballon d'Or, an unprecedented feat for players from the same football school.\r\n\r\nBarcelona was one of the founding members of La Liga, and is one of three clubs which have never been relegated from the top division, along with Athletic Bilbao and Real Madrid. In 2009, Barcelona became the first Spanish club to win the continental treble consisting of La Liga, Copa del Rey, and the Champions League. That same year, it also became the first football club ever to win six out of six competitions in a single year, thus completing the sextuple, comprising the aforementioned treble and the Spanish Super Cup, UEFA Super Cup and FIFA Club World Cup. In 2011, the Blaugrana again became European champions and won a total of five titles, missing out only on the Copa del Rey (in which they finished runners-up). This Barcelona team, which reached a record six consecutive Champions League semi-finals and won 14 trophies in just four years under Guardiola's charge, is considered by some managers, players and experts to be the greatest team of all time.",
            "1899",
            "Spanish La Liga",
            "https://www.thesportsdb.com/images/media/team/badge/xqwpup1473502878.png",
            "https://www.thesportsdb.com/images/media/team/jersey/y6o3f21625514197.png",
            "www.fcbarcelona.com",
            "www.facebook.com/fcbarcelona",
            "twitter.com/fcbarcelona",
            "instagram.com/fcbarcelona",
            "www.youtube.com/user/fcbarcelona"
            )
        dao.insert(team)

        val teamVer = dao.getDataFromXTeam("Barcelona")

        assertThat(teamVer).isEqualTo(team)
    }

    @Test
    fun updateATeamData () = runBlockingTest {
        val team = Team (
            "Barcelona",
            "Camp Nou",
            "Futbol Club Barcelona, also known as Barcelona and familiarly as Barça, is a professional football club, based in Barcelona, Catalonia, Spain.\r\n\r\nFounded in 1899 by a group of Swiss, English and Catalan footballers led by Joan Gamper, the club has become a symbol of Catalan culture and Catalanism, hence the motto \"Més que un club\" (More than a club). Unlike many other football clubs, the supporters own and operate Barcelona. It is the second most valuable sports team in the world, worth $3.2 billion, and the world's second-richest football club in terms of revenue, with an annual turnover of $613 million. The official Barcelona anthem is the \"Cant del Barça\", written by Jaume Picas and Josep Maria Espinàs.\r\n\r\nBarcelona is the joint most successful club in Spain, in terms of overall official titles won (80). It has won 22 La Liga, 26 Copa del Rey, 11 Supercopa de España, 2 Copa Eva Duarte and 2 Copa de la Liga trophies, as well as being the record holder for the latter four competitions. In international club football, Barcelona has won four UEFA Champions League, a record four UEFA Cup Winners' Cup, four UEFA Super Cup, a record three Inter-Cities Fairs Cup and a record two FIFA Club World Cup trophies. Barcelona was ranked first in the IFFHS Club World Ranking for 1997, 2009, 2011 and 2012 and currently occupies the second position on the UEFA club rankings. The club has a long-standing rivalry with Real Madrid; matches between the two teams are referred to as \"El Clásico\".\r\n\r\nBarcelona is one of the most supported teams in the world, and has the largest social media following in the world among sports teams. Barcelona's players have won a record number of Ballon d'Or awards (10), as well as a record number of FIFA World Player of the Year awards (7). In 2010, the club created history when three players who came through its youth academy (Messi, Iniesta & Xavi) were chosen as the three best players in the world, having bagged the top spots at the FIFA Ballon d'Or, an unprecedented feat for players from the same football school.\r\n\r\nBarcelona was one of the founding members of La Liga, and is one of three clubs which have never been relegated from the top division, along with Athletic Bilbao and Real Madrid. In 2009, Barcelona became the first Spanish club to win the continental treble consisting of La Liga, Copa del Rey, and the Champions League. That same year, it also became the first football club ever to win six out of six competitions in a single year, thus completing the sextuple, comprising the aforementioned treble and the Spanish Super Cup, UEFA Super Cup and FIFA Club World Cup. In 2011, the Blaugrana again became European champions and won a total of five titles, missing out only on the Copa del Rey (in which they finished runners-up). This Barcelona team, which reached a record six consecutive Champions League semi-finals and won 14 trophies in just four years under Guardiola's charge, is considered by some managers, players and experts to be the greatest team of all time.",
            "1899",
            "Spanish La Liga",
            "https://www.thesportsdb.com/images/media/team/badge/xqwpup1473502878.png",
            "https://www.thesportsdb.com/images/media/team/jersey/y6o3f21625514197.png",
            "www.fcbarcelona.com",
            "www.facebook.com/fcbarcelona",
            "twitter.com/fcbarcelona",
            "instagram.com/fcbarcelona",
            "www.youtube.com/user/fcbarcelona"
        )
        dao.insert(team)

        dao.updateATeam(
            Team (
                "Barcelona",
                "Camp Nou",
                "Futbol Club Barcelona, also known as Barcelona and familiarly as Barça, is a professional football club, based in Barcelona, Catalonia, Spain.\r\n\r\nFounded in 1899 by a group of Swiss, English and Catalan footballers led by Joan Gamper, the club has become a symbol of Catalan culture and Catalanism, hence the motto \"Més que un club\" (More than a club). Unlike many other football clubs, the supporters own and operate Barcelona. It is the second most valuable sports team in the world, worth $3.2 billion, and the world's second-richest football club in terms of revenue, with an annual turnover of $613 million. The official Barcelona anthem is the \"Cant del Barça\", written by Jaume Picas and Josep Maria Espinàs.\r\n\r\nBarcelona is the joint most successful club in Spain, in terms of overall official titles won (80). It has won 22 La Liga, 26 Copa del Rey, 11 Supercopa de España, 2 Copa Eva Duarte and 2 Copa de la Liga trophies, as well as being the record holder for the latter four competitions. In international club football, Barcelona has won four UEFA Champions League, a record four UEFA Cup Winners' Cup, four UEFA Super Cup, a record three Inter-Cities Fairs Cup and a record two FIFA Club World Cup trophies. Barcelona was ranked first in the IFFHS Club World Ranking for 1997, 2009, 2011 and 2012 and currently occupies the second position on the UEFA club rankings. The club has a long-standing rivalry with Real Madrid; matches between the two teams are referred to as \"El Clásico\".\r\n\r\nBarcelona is one of the most supported teams in the world, and has the largest social media following in the world among sports teams. Barcelona's players have won a record number of Ballon d'Or awards (10), as well as a record number of FIFA World Player of the Year awards (7). In 2010, the club created history when three players who came through its youth academy (Messi, Iniesta & Xavi) were chosen as the three best players in the world, having bagged the top spots at the FIFA Ballon d'Or, an unprecedented feat for players from the same football school.\r\n\r\nBarcelona was one of the founding members of La Liga, and is one of three clubs which have never been relegated from the top division, along with Athletic Bilbao and Real Madrid. In 2009, Barcelona became the first Spanish club to win the continental treble consisting of La Liga, Copa del Rey, and the Champions League. That same year, it also became the first football club ever to win six out of six competitions in a single year, thus completing the sextuple, comprising the aforementioned treble and the Spanish Super Cup, UEFA Super Cup and FIFA Club World Cup. In 2011, the Blaugrana again became European champions and won a total of five titles, missing out only on the Copa del Rey (in which they finished runners-up). This Barcelona team, which reached a record six consecutive Champions League semi-finals and won 14 trophies in just four years under Guardiola's charge, is considered by some managers, players and experts to be the greatest team of all time.",
                "1852",
                "La Liga de España",
                "https://www.thesportsdb.com/images/media/team/badge/xqwpup1473502878.png",
                "https://www.thesportsdb.com/images/media/team/jersey/y6o3f21625514197.png",
                "www.fcbarcilona.com",
                "www.facebook.com/fcbarcelona",
                "twitter.com/fcbarcelona",
                "instagram.com/fcbarcelona",
                "www.youtube.com/user/fcbarcelonat"
            )
        )
        val teamVer = dao.getDataFromXTeam("Barcelona")
        assertThat(teamVer.name).isEqualTo(team.name)
        assertThat(teamVer.stadiumName).isEqualTo(team.stadiumName)
        assertThat(teamVer.teamDescription).isEqualTo(team.teamDescription)
        assertThat(teamVer.foundationYear).doesNotMatch(team.foundationYear)
        assertThat(teamVer.league).doesNotMatch(team.league)
        assertThat(teamVer.teamBadge).isEqualTo(team.teamBadge)
        assertThat(teamVer.teamJersey).isEqualTo(team.teamJersey)
        assertThat(teamVer.websiteLink).doesNotMatch(team.websiteLink)
        assertThat(teamVer.facebookLink).isEqualTo(team.facebookLink)
        assertThat(teamVer.twitterLink).isEqualTo(team.twitterLink)
        assertThat(teamVer.instagramLink).isEqualTo(team.instagramLink)
        assertThat(teamVer.youtubeLink).doesNotMatch(team.youtubeLink)
    }
}