package com.example.e_commerce_gh.presentation.screens.register

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

import android.app.Activity
import android.app.Application

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(application: Application,
                      private val firebaseAuth: FirebaseAuth,
                      private val firestore: FirebaseFirestore) : ViewModel() {
    private val _verifyCodeUiState = MutableStateFlow(VerifyCodeUiState())
    val verifyCodeUiState: StateFlow<VerifyCodeUiState> = _verifyCodeUiState.asStateFlow()


    fun onEvent(event: VerifyCodeUiEvent) {
        when (event) {
            is VerifyCodeUiEvent.SubmitChanged -> {
                _verifyCodeUiState.value = verifyCodeUiState.value.copy(
                    phoneNumber = event.phoneNumber
                )
            }
            is VerifyCodeUiEvent.CodeChanged -> {
                _verifyCodeUiState.value = verifyCodeUiState.value.copy(
                    verificationCode = event.code
                )
                verifyCode()
            }
        }
    }

    fun authenticatePhoneNumber(activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(verifyCodeUiState.value.phoneNumber)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    _verifyCodeUiState.value = verifyCodeUiState.value.copy(
                        verificationId = verificationId,
                        isCodeSent = true
                    )
                    // Save the verification id somewhere
                    // Enable user manually input code or handle UI update
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(phoneAuthCredential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // Handle error
                    // ...
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    fun verifyCode() {
        val code = verifyCodeUiState.value.verificationCode
        val verificationId = verifyCodeUiState.value.verificationId

        if (verificationId != null && code.isNotEmpty()) {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            signInWithPhoneAuthCredential(credential)
        }
    }
    private fun savePhoneNumberToFirestore(userId: String, phoneNumber: String) {
        val db = FirebaseFirestore.getInstance()
        val customer = hashMapOf(
            "phoneNumber" to phoneNumber
        )

        firestore.collection("CustomersNumber").document(userId)
            .set(customer)
            .addOnSuccessListener {
                    //
            }
            .addOnFailureListener { e ->
                // Handle the error
            }
    }
    private fun signInWithPhoneAuthCredential(phoneAuthCredential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-in succeeded
                    val user = task.result?.user
                    user?.let {
                        val userId = it.uid
                        val phoneNumber = it.phoneNumber ?: verifyCodeUiState.value.phoneNumber
                        savePhoneNumberToFirestore(userId, phoneNumber)
                    }
                } else {
                    // Sign-in failed
                }
            }
    }
}
