package com.fueltrac.model.entities

 class FilterModel{
     var Date: String="sdfd"
     var Type: String="sdf"
     var Volume: String="sdf"
     var WaterLevel :String ="sdf"
     var Level: String ="1"
     var Ullage : String = "5"
     var Temperature :String ="sadf"

     constructor(
         Date: String,
         Type: String,
         Volume: String,
         WaterLevel: String,
         Level: String,
         Ullage: String,
         Temperature: String
     ) {
         this.Date = Date
         this.Type = Type
         this.Volume = Volume
         this.WaterLevel = WaterLevel
         this.Level = Level
         this.Ullage = Ullage
         this.Temperature = Temperature
     }
 }


