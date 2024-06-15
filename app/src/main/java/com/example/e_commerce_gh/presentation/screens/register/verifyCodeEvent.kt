package com.example.e_commerce_gh.presentation.screens.register

sealed class VerifyCodeUiEvent{
    data class SubmitChanged(var phoneNumber: String):VerifyCodeUiEvent()
    data class CodeChanged(val code: String) : VerifyCodeUiEvent()
}