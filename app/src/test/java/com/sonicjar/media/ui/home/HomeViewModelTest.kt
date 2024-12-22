package com.sonicjar.media.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.sonicjar.media.utils.FakeRepository
import com.sonicjar.media.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var repository: FakeRepository
    private lateinit var viewModel: HomeViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun testGetList() = runTest{
        viewModel.lists.test {
            viewModel.getLists()
            Assert.assertEquals(FakeRepository.response, awaitItem())
        }
    }
}