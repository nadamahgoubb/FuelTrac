package com.fueltrac.model.network


import com.fueltrac.model.entities.*
import com.fueltrac.model.entities.SearchSend
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.model.entities.UserSend
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface ApiInterface {

  @POST("userLogin")
    fun loginResult( @Body userSend: UserSend):
          Single<GeneralResponse<UserResponse>>

  @POST("SearchVehicle")
    fun SearchVehicle( @Body SearchSend: SearchSend):
          Single<GeneralResponse<Searchresult>>

  @POST("finishTransaction")
    fun FinishTransaction( @Body finishTransactionReq: TransactionModel?):
          Single<GeneralResponse<FinishTransactionRes>>

  @POST("filterData")
    fun filterData( @Body filterDataRequest: FilterDataRequest?):
          Single<GeneralResponse<FillterTankInfo>>

    @FormUrlEncoded
  @POST("getTankInfo")
    fun getTankInfo( @Field("tankId" )tankId : String ,@Field("token" ) token : String ):
          Single<GeneralResponse<TankInfo>>

    @FormUrlEncoded
  @POST("CheckDriver")
    fun CheckDriver( @Field("driverNumber" )tankId : String ,@Field("token" ) token : String ):
          Single<GeneralResponse<DriverNumber>>

}
