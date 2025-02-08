package com.sonicjar.media

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

abstract class BaseActivity<V : BaseViewModel> : ComponentActivity(){
    private lateinit var mProgressDialog: Dialog
    lateinit var mViewModel: V

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        mViewModel.showToast.observe(this) {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
        mViewModel.showProgress.observe(this) {
            it?.let {
                showProgress(it)
            }
        }
    }

    fun showProgress(state: Boolean) {
        if (state) {
            if(!mProgressDialog.isShowing)
                mProgressDialog.show()
        } else {
            if(mProgressDialog.isShowing)
                mProgressDialog.dismiss()
        }
    }

}