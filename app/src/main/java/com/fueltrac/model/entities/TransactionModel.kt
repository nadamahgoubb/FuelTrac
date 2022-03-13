package com.fueltrac.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fueltrac.utilities.user_Token
import com.fueltrac.utilities.user_id

@Entity(tableName = "TransactionsTable")
data class TransactionModel(


var PlateNumber: String="",
    var DriveNumber: String="",
    var quantity: String="",
    var StartDateTime :String ="",
    var pumpNumber: String ="-1",
    var StationId : String = "-1",
    var type :String ="",
    var department :String ="",
    var token : String = user_Token,
    var userID : String = user_id.toString()
   )
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //**do not made it val**
}
