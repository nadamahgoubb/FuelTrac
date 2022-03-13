package com.fueltrac.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.fueltrac.model.db.GeneralDataBase
import com.fueltrac.model.entities.*
import com.fueltrac.model.network.ApiBase
import com.fueltrac.model.network.ApiClient
import com.fueltrac.model.network.ApiInterface
import com.fueltrac.model.entities.SearchSend
import com.fueltrac.model.entities.UserResponse
import com.fueltrac.model.entities.UserSend
import com.fueltrac.utilities.user_Token
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Repository (var context: Context){

    private var apiClient: ApiClient? = null
    private var apiInterface: ApiInterface? = null

    private lateinit var generalDb: GeneralDataBase

    init {
        apiClient = ApiClient()
        apiInterface =
            apiClient!!.getClient(ApiBase.BASE_URL)!!.create(ApiInterface::class.java)
        generalDb= GeneralDataBase.getDataClient(context)
    }

    fun login(loginRequest: UserSend): Single<GeneralResponse<UserResponse>> {
        return apiInterface!!.loginResult(loginRequest)
    }

    fun SearchVehicles(searchsend: SearchSend): Single<GeneralResponse<Searchresult>> {
        return apiInterface!!.SearchVehicle(searchsend)
    }


    fun FinishTransaction(finishTransactionReq: TransactionModel?): Single<GeneralResponse<FinishTransactionRes>> {
        return apiInterface!!.FinishTransaction(finishTransactionReq)
    }

    fun filterData (filterDataRequest: FilterDataRequest?): Single<GeneralResponse<FillterTankInfo>> {
        return apiInterface!!.filterData(filterDataRequest)
    }

    fun getTankInfo(tankId: String): Single<GeneralResponse<TankInfo>> {
        return apiInterface!!.getTankInfo(tankId, user_Token)
    }

    fun CheckDriver(driverNumber : String): Single<GeneralResponse<DriverNumber>> {
        return apiInterface!!.CheckDriver( driverNumber , user_Token )
    }



    fun insertUser( userresponse: UserResponse) {
        CoroutineScope(Dispatchers.IO).launch {
                generalDb!!.userDao().insertUser(User(userresponse.userId,userresponse.token,userresponse.userType))

            var stationsr: List<StationsResponse>
            stationsr= userresponse.getStations()!!
            var statio: ArrayList<Stations> = arrayListOf()
            var tankslist: ArrayList<tanks> = arrayListOf()
            var pumbslist: ArrayList<pumps> = arrayListOf()
            for (element in stationsr) {
                statio.add(Stations(element.id,element.stationName,userresponse.userId))
                for (tan in element.tanks!!){
                    tankslist.add(tanks(tan.id,tan.tankName,element.id))
                }
                for (pum in element.pumps!!){
                    pumbslist.add(pumps(pum.id,pum.pumpName,element.id))
                }
            }
            generalDb!!.stationDao().insertStations(statio)
            generalDb!!.tanksDao().inserttanks(tankslist)
            generalDb!!.pumpsDao().insertpumps(pumbslist)

        }
    }

    /**\
     * TODO
     */
//    fun GetUserbyID(context: Context, Id: Integer): LiveData<UserResponse>? {
//                return  generalDb!!.userresponseDao().getUserById(Id)
//    }


    fun GetPumps(StationId: Int): LiveData<List<pumps>>? {
        return generalDb!!.pumpsDao().getpumpByStationId(StationId)
    }

    fun getStationById(UserID: String): LiveData<List<Stations>>? {
        return generalDb!!.stationDao().getStationById(UserID)
    }


    fun getTanksById(stationid: Int): LiveData<List<tanks>>? {
        return generalDb!!.tanksDao().gettanksByStationId(stationid)
    }




    fun insertTransactionData( transactionModel: TransactionModel) {

        CoroutineScope(Dispatchers.IO).launch {
            generalDb!!.transactionDao().insertTransactionData(transactionModel)
        }

    }
    fun removeTransactionData( transactionModel: TransactionModel) {

        CoroutineScope(Dispatchers.IO).launch {
            generalDb!!.transactionDao().deleteTransactionsByid(transactionModel)
        }

    }
    fun getTransactionByPlate(  PlateNum: String): LiveData<TransactionModel>? {

        return generalDb!!.transactionDao().getTransactionByPlate(PlateNum)
    }

    fun GetTransactions (token: Int): LiveData<List<TransactionModel>>? {
        return generalDb!!.transactionDao().getTransactionsByToken(token)
    }



}
