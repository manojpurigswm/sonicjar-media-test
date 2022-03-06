package com.sonicjar.media.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonicjar.media.BaseActivity
import com.sonicjar.media.R
import com.sonicjar.media.data.Track
import com.sonicjar.media.databinding.ActivityHomeBinding
import com.sonicjar.media.utils.bind
import com.sonicjar.media.utils.getViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce

class HomeActivity: BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

    val trackList = arrayListOf<Track>()

    lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.isNoData = false

        adapter = TrackAdapter(arrayListOf())

        setUpRecyclerView()

        bind(viewModel.count){
            mBinding.etSearch.hint = "Search $it tracks"
        }

        bind(viewModel.data){
            adapter.list.clear()
            adapter.list.addAll(it)
            adapter.notifyDataSetChanged()
            mBinding.refreshLayout.isRefreshing = false
        }

        bind(viewModel.isFail){
            mBinding.isNoData = it
        }

        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.submit()
        }

        mBinding.imageCancel.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                mBinding.etSearch.setText("")
                hideKeyboard()
            }
        }

        lifecycleScope.launchWhenCreated {
            mViewModel.searchQuery.debounce(500).collect {
                mViewModel.submit()
            }
        }



        mBinding.etSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                mViewModel.setQuery(mBinding.etSearch.text.toString().trim())
                if(mBinding.etSearch.text.toString().trim().isNullOrEmpty()){
                    mBinding.imageCancel.visibility = View.GONE
                }
                else{
                    mBinding.imageCancel.visibility = View.VISIBLE
                }
            }

        })


        mBinding.etSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH){

                bind(mViewModel.searchQuery){
                    if(it != mBinding.etSearch.text.toString().trim()){
                        mViewModel.setQuery(mBinding.etSearch.text.toString().trim())
                        mViewModel.submit()
                    }
                }

                hideKeyboard()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }


    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.adapter = adapter
    }

    private fun hideKeyboard(){
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
        mBinding.etSearch.clearFocus()
    }


    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
            isTaskRoot &&
            supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount ?: 0 == 0 &&
            supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}