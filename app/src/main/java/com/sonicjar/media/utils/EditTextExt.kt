package com.sonicjar.media.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.afterTextChanged() : Flow<Editable?> = callbackFlow {
    val callBack = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            offer(p0)
        }
    }

    addTextChangedListener(callBack)

    awaitClose {
        removeTextChangedListener(callBack)
    }
}

fun EditText.textChanged() : Flow<CharSequence?> = callbackFlow {
    val callBack = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            offer(p0)
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    addTextChangedListener(callBack)

    awaitClose {
        removeTextChangedListener(callBack)
    }
}