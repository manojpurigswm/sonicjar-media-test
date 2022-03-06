package com.sonicjar.media

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(){
    private lateinit var mProgressDialog: Dialog

    lateinit var mBinding: T
    lateinit var mViewModel: V

    @get:LayoutRes
    abstract val layoutId: Int
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this

        mProgressDialog = Dialog(this)
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog.setContentView(R.layout.layout_progress_dialog)
        mProgressDialog.setCancelable(false)
        mProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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