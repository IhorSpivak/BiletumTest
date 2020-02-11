package com.yaroslavtupalo.knocs.data

import android.content.SharedPreferences
import com.yaroslavtupalo.knocs.api.KnoxxAPI
import com.yaroslavtupalo.knocs.data.local.db.mappers.UserEntityMapper
import com.yaroslavtupalo.knocs.data.network.model.requests.*
import com.yaroslavtupalo.knocs.data.network.model.responces.*
import com.yaroslavtupalo.knocs.domain.model.AddWalletRequest
import com.yaroslavtupalo.knocs.domain.model.DeleteWalletRequest
import com.yaroslavtupalo.knocs.domain.model.RegistrationRequest
import javax.inject.Inject

class DataRepository(private val apiService:KnoxxAPI,
                     private val userEntityMapper: UserEntityMapper) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    suspend fun login(login:String,password:String): OauthResponse {
       val oAuthResponse =  apiService.login(username =  login ,password =password)


        return oAuthResponse

    }

    suspend fun registration(login:String,password:String):RegistrationResponse{

        val signUpResponse =  apiService.registration("Basic d2ViX3NpdGVfY2xpZW50OndlYl9zaXRlX2NsaWVudF9zZWNyZXRfa2V5", RegistrationRequest(login,password) )

        return signUpResponse

    }

    suspend fun getListMonitor(key:String, algoritm:String): MonitorListResponse {

        val response =  apiService.getMonitorList("Bearer $key", MonitorListRequest(algoritm))

        response.result!!.forEach {
            val string = it.askRecommendation?.currencyPair.toString()
            val index = string.indexOf('_')
            val firstCurrency: String? = if (index == -1) null else string.substring(index + 1)
            if( firstCurrency != "USDT"){
                it.dollarRate = getRates(key, firstCurrency!!).result
            } else {
                it.dollarRate = getRates(key, "USDC").result
            }

        }
        return response

    }

    suspend fun getListExchange(key: String): List<ExchangeListItem> {

        val response = apiService.getListExchange("Bearer $key")

//        if(response.error!=null)
//            throw MyCustomException

        if (response.result.content.isNullOrEmpty())
            return emptyList()

        response.result.content.forEach {
            if (it.userMarketDto?.userMarketSettingsId != null)
                it.balances =
                    getMarketBalance(key, it.userMarketDto.userMarketSettingsId).result.balances

            
        }
        return response.result.content
    }

    suspend fun getRates(key: String,currencyFrom:String): RatesResponce {


        val response =  apiService.getRates("Bearer $key",
            RatesRequest(currencyFrom, "USDT")
        )

        return response

    }


    suspend fun getMarketBalance(key: String, id: Int): MarketBalanceResponse {

        val response =  apiService.getMarketBalances("Bearer $key",
            MarketBalancesRequest(id)
        )
        return response

    }

    suspend fun getListContracts(key:String): ContractsListResponce {

        val response =  apiService.getListContracts("Bearer $key" )

        response.result!!.content!!.forEach {
            if( it.firstCurrency!! != "USDT"){
                it.dollarRates = getRates(key, it.firstCurrency!!).result
            } else {
                it.dollarRates = getRates(key, "USDC").result
            }

        }

        return response

    }

    suspend fun getListWallets(key:String,offset:Int,pageNumber:Int, paged:Boolean,unPaged:Boolean): WalletListResponse {

        val response =  apiService.getWalletList("Bearer $key",
            WalletListRequest(offset, pageNumber, 10, paged, unPaged)
        )

        response.result!!.content!!.forEach {
                it.dollarRates = getRates(key, it.currency!!).result
        }

        return response

    }

    suspend fun getTransactionsIteractor(key:String,id:Int): WalletTransactionResponce {

        val response =  apiService.getTransactionsList("Bearer $key", DeleteWalletRequest(id) )

        return response

    }

    suspend fun addWallet(key : String, name: String, currency: String, address: String): AddWalletResponce {

        val response =  apiService.addWallet("Bearer $key", AddWalletRequest(address,currency,name) )

        return response

    }


    suspend fun pauseContract(key : String,contractId : Int): ContractsStatusResponce {

        val response =  apiService.stopContract("Bearer $key", ChangeContractStatusRequest(contractId) )

        return response

    }

    suspend fun startContract(key : String,contractId : Int): ContractsStatusResponce {

        val response =  apiService.startContract("Bearer $key", ChangeContractStatusRequest(contractId) )

        return response

    }

    suspend fun closeContract(key : String,contractId : Int): ContractsStatusResponce {

        val response =  apiService.closeContract("Bearer $key", ChangeContractStatusRequest(contractId) )

        return response

    }


    suspend fun deleteWallet(key:String,id:Int): DeleteWalletResponse {

        val deleteWalletResponce =  apiService.deleteWallet("Bearer $key", DeleteWalletRequest(id) )

        return deleteWalletResponce

    }



}