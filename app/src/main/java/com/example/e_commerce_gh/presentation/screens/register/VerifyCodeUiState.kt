package com.example.e_commerce_gh.presentation.screens.register

data class VerifyCodeUiState(
    val phoneNumber : String = "",
    val verificationId: String? = null,
    val isCodeSent: Boolean = false,
    val verificationCode: String = "",
    val phoneNumberError: Boolean = false
)