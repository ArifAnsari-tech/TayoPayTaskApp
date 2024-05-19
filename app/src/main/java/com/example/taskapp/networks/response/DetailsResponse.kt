package com.example.taskapp.networks.response

data class DetailsResponse(
    val data: Data,
    val message: String,
    val status: String
){
    data class Data(
        val branch: Branch,
        val email: String,
        val firstName: String,
        val googleTwoFactorAuthentication: Any,
        val id: Int,
        val isActive: Boolean,
        val isAdmin: Boolean,
        val isRegional: Boolean,
        val isSenderAddInfoAvailable: Boolean,
        val isSenderDocInfoAvailable: Boolean,
        val isTwoFactorAuthenticationEnabled: Boolean,
        val lastName: String,
        val mobilePhone: String,
        val nationalityId: Any,
        val otpTypeId: Any,
        val permissions: List<String>,
        val residentialStatusId: Any,
        val roleName: String,
        val userTypeName: String,
        val username: String
    )
    data class Branch(
        val baseCurrencyId: Int,
        val branchIp: Any,
        val branchStatus: Boolean,
        val cityId: Int,
        val code: String,
        val commissionPolicyId: Int,
        val countryId: Int,
        val cpoAccountId: Int,
        val createdBy: Int,
        val createdDate: String,
        val id: Int,
        val isVisible: Boolean,
        val lastModifiedDate: String,
        val limit: Int,
        val parentId: Int,
        val payCashCommission: Int,
        val phone: String,
        val sendCashCommission: Int,
        val title: String,
        val typeId: Int
    )
}