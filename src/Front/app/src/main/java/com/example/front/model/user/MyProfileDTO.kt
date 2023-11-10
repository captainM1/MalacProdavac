package com.example.front.model.user

import com.example.front.model.RatingDTO
import com.google.gson.annotations.SerializedName
import java.util.Date

data class MyProfileDTO (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("role") val role: String,
    @SerializedName("roleId") val roleId: Int,
    @SerializedName("username") val username: String,
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("rating") val rating: RatingDTO,
    @SerializedName("createdOn") val createdOn: String,
    @SerializedName("moneySpent") val moneySpent: Float,
    @SerializedName("moneyEarned") val moneyEarned: Float,
)