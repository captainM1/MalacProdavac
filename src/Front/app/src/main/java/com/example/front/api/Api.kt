package com.example.front.api

import com.example.front.model.DTO.CategoriesDTO
import com.example.front.model.DTO.ChosenCategoriesDTO
import com.example.front.model.DTO.HomeProductDTO
import com.example.front.model.DTO.LeaveReviewDTO
import com.example.front.model.DTO.LoginDTO
import com.example.front.model.DTO.MetricsDTO
import com.example.front.model.DTO.NewProductDTO
import com.example.front.model.DTO.ProductDTO
import com.example.front.model.DTO.ProductDisplayDTO
import com.example.front.model.DTO.ReviewDTO
import com.example.front.model.request.RegistrationRequest
import com.example.front.model.response.LoginResponse
import com.example.front.model.DTO.ShopDTO
import com.example.front.model.DTO.ShopDetailsCheckoutDTO
import com.example.front.model.DTO.ShopDetailsDTO
import com.example.front.model.DTO.ShopPagesDTO
import com.example.front.model.DTO.ToggleLikeDTO
import com.example.front.model.product.ProductInfo
import com.example.front.model.product.ProductReviewUserInfo
import com.example.front.model.response.Id
import com.example.front.model.response.Success
import com.example.front.model.response.SuccessBoolean
import com.example.front.model.response.SuccessProductDisplay
import com.example.front.model.user.MyProfileDTO
import com.example.front.model.user.UserEditDTO
import okhttp3.MultipartBody
import org.w3c.dom.Comment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface Api {
    @Headers("Content-Type: application/json")
    @POST("back/Auth/Login")
    suspend fun getLoginInfo(
        @Body login: LoginDTO
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("back/Auth/Register")
    suspend fun register(
        @Body registrationRequest: RegistrationRequest
    ): Response<LoginResponse>

    @GET("back/Home/GetCategories")
    suspend fun getCategories(

    ): Response<List<CategoriesDTO>>

    @POST("/back/Home/SaveChosenCategories")
    suspend fun saveChosenCategories(
        @Body chosenCategories: ChosenCategoriesDTO
    ): Response<Boolean>

    @GET("back/Home/GetHomeProducts")
    suspend fun getHomeProducts(
        @Query("id") id: Int
    ): Response<List<HomeProductDTO>>

    @GET("back/Home/GetHomeShops")
    suspend fun getHomeShops(
        @Query("id") id: Int
    ): Response<List<ShopDTO>>

    @Headers("Content-Type: application/json")
    @GET("/back/Product/ProductDetails")
    suspend fun getProductDetails(
        @Query("productId") productID: Int,
        @Query("userId") userID: Int
    ): Response<ProductInfo>

    @GET("back/User/MyProfile")
    suspend fun getMyProfileInfo(
        @Query("userId") productID: Int,
    ): Response<MyProfileDTO>

    @PUT("back/Auth/Edit")
    suspend fun editUserProfile(
        @Body data : UserEditDTO
    ):Response<LoginResponse>

    @POST("back/Shop/ToggleLike")
    suspend fun toggleLike(
        @Query("shopId") shopId : Int,
        @Query("userId") userId : Int,
    ): Response<ToggleLikeDTO>

    @GET("/back/Product/ProductReviews")
    suspend fun getReviewsForProduct(
        @Query("productId") productId: Int,
        @Query("page") page: Int
    ): Response<List<ProductReviewUserInfo>>

    @GET("back/Shop/GetShops")
    suspend fun getShops(
        @Query("userId") userId: Int,
        @Query("categories") categories: List<Int>?,
        @Query("rating") rating: Int?,
        @Query("open") open: Boolean?,
        @Query("range") range: Int?,
        @Query("location") location: String?,
        @Query("sort") sort: Int,
        @Query("search") search: String?,
        @Query("page") page: Int,
        @Query("favorite") favorite: Boolean?,
        @Query("currLat") currLat: Float?,
        @Query("currLong") currLong: Float?
    ):Response<List<ShopDTO>>

    @PUT("/back/Auth/FCMTokenSave")
    suspend fun saveFCMToken(
        @Query("userId") userID: Int,
        @Query("token") token: String
    ): Response<Boolean>

    @GET("back/Shop/ShopPages")
    suspend fun getShopPages(
        @Query("userId") userId: Int,
        @Query("categories") categories: List<Int>?,
        @Query("rating") rating: Int?,
        @Query("open") open: Boolean?,
        @Query("range") range: Int?,
        @Query("location") location: String?,
        @Query("search") search: String?,
        @Query("favorite") favorite: Boolean?,
        @Query("currLat") currLat: Float?,
        @Query("currLong") currLong: Float?
    ):Response<ShopPagesDTO>

    @GET("back/Shop/ShopDetails")
    suspend fun getShopDetails(
        @Query("shopId") shopId: Int,
        @Query("userId") userId: Int
    ):Response<ShopDetailsDTO>

    @GET("back/Product/GetProducts")
    suspend fun getProducts(
        @Query("userId") userId: Int,
        @Query("categories") categories: List<Int>?,
        @Query("rating") rating: Int?,
        @Query("open") open: Boolean?,
        @Query("range") range: Int?,
        @Query("location") location: String?,
        @Query("sort") sort: Int?,
        @Query("search") search: String?,
        @Query("page") page: Int?,
        @Query("specificShopId") specificShopId: Int?,
        @Query("favorite") favorite: Boolean?,
        @Query("currLat") currLat: Float?,
        @Query("currLong") currLong: Float?
    ):Response<List<ProductDTO>>

    @GET("back/Shop/ShopReviews")
    suspend fun getShopReviews(
        @Query("shopId") shopId: Int,
        @Query("page") page: Int
    ):Response<List<ReviewDTO>>

    @POST("back/Shop/Review")
    suspend fun postShopReview(
        @Body data: LeaveReviewDTO,
    ): Response<Success>

    @GET("/back/Helper/Metrics")
    suspend fun getMetrics(
    ):Response<List<MetricsDTO>>

    @POST("/back/Product/AddProduct")
    suspend fun postNewProduct(
        @Body data: NewProductDTO,
    ): Response<Int>

    @GET("back/Shop/GetShopid")
    suspend fun getShopId(
        @Query("userId") userId: Int,
    ):Response<Id>

    @POST("/back/Helper/UploadImage")
    @Multipart
    suspend fun uploadImage(
        @Query("type") shopId: Int,
        @Query("id") page: Int,
        @Part image: MultipartBody.Part
    ): Response<Success>

    @GET("back/Shop/GetProductDisplay")
    suspend fun getProductDisplay(
        @Query("id") id: Int,
    ): Response<ProductDisplayDTO>

    @DELETE("back/Shop/DeleteProductDisplay")
    suspend fun deleteProductDisplay(
        @Query("id") id: Int,
    ):Response<SuccessBoolean>

    @GET("back/Shop/GetShopsForCheckout")
    suspend fun getShopsForCheckout(
        @Query("shopIds") shopIds: List<Int>
    ):Response<List<ShopDetailsCheckoutDTO>>
}