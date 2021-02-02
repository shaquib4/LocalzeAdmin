package com.example.localzeadmin.Modals

class ModalSellerInfo(
    val shopId: String,
    val shopName: String,
    val sellerName: String,
    val phoneNumber: String,
    val shopAddress: String
) {
    constructor() : this("", "", "", "", "")
}