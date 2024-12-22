package com.sonicjar.media.data.source.remote

import com.google.gson.reflect.TypeToken
import com.sonicjar.media.data.Resource
import com.sonicjar.media.data.Track
import com.sonicjar.media.data.source.BaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitCalls: RetrofitCalls, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): BaseDataSource {
    override suspend fun getTracks(): Resource<List<Track>> = withContext(ioDispatcher) {
        return@withContext safeApiCall { retrofitCalls.getTracks() }
    }

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {
                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    Resource.Success(response.body()!!)
                } else {
                    // parsing api's own custom json error
                    // response in ExampleErrorResponse pojo
                    val message = StringBuilder()
                    val error = response.errorBody()?.string()
                    error?.let {
                        try {
                            if (response.code() == 400 || response.code() == 404 || response.code() == 500) {
                                val itemType = object : TypeToken<T>() {}.type
                                val throwable = Throwable(error)
                                Resource.Fail(throwable)
                            } else if (response.code() == 401 || response.code() == 402 || response.code() == 403) {
                                val errorMsg: String? = JSONObject(it).optString("message")
                                if (!errorMsg.isNullOrEmpty()) {
                                    message.append(errorMsg)
                                } else {
                                    message.append("Session Expired.")
                                }

                                val throwable = Throwable(error)
                                Resource.Fail(throwable)
                            } else {
                                val errorMsg: String? = JSONObject(it).optString("message")
                                if (!errorMsg.isNullOrEmpty()) {
                                    message.append(errorMsg)
                                } else {
                                    message.append("Session Expired.")
                                }
                                val throwable = Throwable(error)
                                Resource.Fail(throwable)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            val throwable = Throwable(error)
                            Resource.Fail(throwable)
                        }
                    }
                }

            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                val throwable = Throwable("Something went wrong")
                Resource.Fail(throwable)
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                val throwable = Throwable("Please check your network connection")
                Resource.Fail(throwable)
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                val throwable = Throwable("Something went wrong")
                Resource.Fail(throwable)
            }!!
        }
    }
}