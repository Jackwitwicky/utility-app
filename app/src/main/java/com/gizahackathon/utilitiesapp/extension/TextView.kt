package com.gizahackathon.utilitiesapp.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * Adds a TextWatcher to the list of those whose methods are called
 * whenever this TextView's text changes.
 */
inline fun TextView.addTextChangedListener(crossinline callback: (textView: TextView) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            callback(this@addTextChangedListener)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // intentionally left blank
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // intentionally left blank
        }
    })
}

/**
 * Set error message using a string resousce
 */
fun TextView.setStringError(errorString: Int?) {
    error = if (errorString == null) null else resources.getString(errorString)
}