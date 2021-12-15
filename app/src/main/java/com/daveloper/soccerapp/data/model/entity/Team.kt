package com.daveloper.soccerapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "teams")
data class Team (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="team_id") val id: Int,
    @ColumnInfo(name= "team_name") @SerializedName("strTeam") var name: String?,
    @ColumnInfo(name= "stadium_name") @SerializedName("strStadium") var stadiumName: String?,
    @ColumnInfo(name= "team_description") @SerializedName("strDescriptionEN") var teamDescription: String?,
    @ColumnInfo(name= "foundation_year") @SerializedName("intFormedYear") var foundationYear: String?,
    @ColumnInfo(name= "league") @SerializedName("strLeague") var league: String?,
    @ColumnInfo(name= "team_badge") @SerializedName("strTeamBadge") var teamBadge: String?,
    @ColumnInfo(name= "team_jersey") @SerializedName("strTeamJersey") var teamJersey: String?,
    @ColumnInfo(name= "website_link") @SerializedName("strWebsite") var websiteLink: String?,
    @ColumnInfo(name= "facebook_link") @SerializedName("strFacebook") var facebookLink: String?,
    @ColumnInfo(name= "twitter_link") @SerializedName("strTwitter") var twitterLink: String?,
    @ColumnInfo(name= "instagram_link") @SerializedName("strInstagram") var instagramLink: String?,
    @ColumnInfo(name= "youtube_link") @SerializedName("strYoutube") var youtubeLink: String?
)