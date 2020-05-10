package com.gizahackathon.utilitiesapp.ui.addbill

// Form validation state
data class ValidationResult(
    val accountNameError: Int? = null,
    val accountAmountError: Int? = null,
    val isDataValid: Boolean = false
)