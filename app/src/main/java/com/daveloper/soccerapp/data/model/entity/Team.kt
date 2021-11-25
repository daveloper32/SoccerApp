package com.daveloper.soccerapp.data.model.entity

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("strTeam") var name: String?,
    @SerializedName("strStadium") var stadiumName: String?,
    @SerializedName("strDescriptionEN") var teamDescription: String?,
    @SerializedName("intFormedYear") var foundationYear: String?,
    @SerializedName("strTeamBadge") var teamBadge: String?,
    @SerializedName("strTeamJersey") var teamJersey: String?,
    @SerializedName("strWebsite") var websiteLink: String?,
    @SerializedName("strFacebook") var facebookLink: String?,
    @SerializedName("strTwitter") var twitterLink: String?,
    @SerializedName("strInstagram") var instagramLink: String?,
    @SerializedName("strYoutube") var youtubeLink: String?

)