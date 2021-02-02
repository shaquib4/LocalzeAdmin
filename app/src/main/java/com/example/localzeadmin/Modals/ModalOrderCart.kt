package com.example.localzeadmin.Modals

class ModalOrderCart (
    val orderId: String,
    val orderTime: String,
    val orderStatus: String,
    val orderCost: String,
    val orderBy: String,
    val orderTo: String,
    val orderQuantity:String,
    val deliveryAddress:String,
    val paymentMode:String,
    val orderByName:String,
    val orderByMobile:String
) {
    constructor():this("","","","","","","","","","","")
}