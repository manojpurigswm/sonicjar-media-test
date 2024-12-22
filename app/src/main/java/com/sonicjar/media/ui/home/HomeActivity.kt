package com.sonicjar.media.ui.home

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonicjar.media.BaseActivity
import com.sonicjar.media.R
import com.sonicjar.media.databinding.ActivityHomeBinding
import com.sonicjar.media.utils.bind
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
open class HomeActivity: BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.isNoData = false

        adapter = TrackAdapter(arrayListOf())

        setUpRecyclerView()

        bind(viewModel.lists){
            if(it.isSuccess){
                adapter.list.clear()
                adapter.list.addAll(it.valueOrNull.orEmpty())
                adapter.notifyDataSetChanged()
                mBinding.refreshLayout.isRefreshing = false
                viewModel.showProgress.value = false
            }
            else if(it.isFail){
                mBinding.refreshLayout.isRefreshing = false
                viewModel.showProgress.value = false
                viewModel.showToast.value = "failed"
            }
            else if(it.isLoading){
                viewModel.showProgress.value = true
            }
        }

        mBinding.refreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                mViewModel.getLists()
            }
        }


        lifecycleScope.launch {
            mViewModel.getLists()
        }
    }


    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.adapter = adapter
    }


    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
            isTaskRoot &&
            (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount
                ?: 0) == 0 &&
            supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}