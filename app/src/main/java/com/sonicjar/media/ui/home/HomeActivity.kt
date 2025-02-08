package com.sonicjar.media.ui.home

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonicjar.media.BaseActivity
import com.sonicjar.media.BaseTheme
import com.sonicjar.media.R
import com.sonicjar.media.data.Track
import com.sonicjar.media.databinding.ActivityHomeBinding
import com.sonicjar.media.utils.bind
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity: BaseActivity<HomeViewModel>() {
    //override val layoutId: Int get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent { BaseTheme {
            TracksScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel
            )
        } }
/*
        adapter = TrackAdapter(arrayListOf())

        setUpRecyclerView()

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.lists.collectLatest {
                if(it.isSuccess){
                    adapter.list.clear()
                    adapter.list.addAll(it.valueOrNull.orEmpty())
                    adapter.notifyDataSetChanged()
                    //mBinding.refreshLayout.isRefreshing = false
                    viewModel.showProgress.value = false
                }
                else if(it.isFail){
                    //mBinding.refreshLayout.isRefreshing = false
                    viewModel.showProgress.value = false
                    viewModel.showToast.value = "failed"
                }
                else if(it.isLoading){
                    viewModel.showProgress.value = true
                }
            }
        }


        mBinding.refreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                mViewModel.getLists()
            }
        }*/

    }

    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        //mBinding.recyclerView.layoutManager = layoutManager
        //mBinding.recyclerView.adapter = adapter
    }
}