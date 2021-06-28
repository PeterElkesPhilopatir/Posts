package peter.com.facebookv2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import peter.com.facebookv2.network.ApiService
import peter.com.facebookv2.pojo.PostModel
import java.lang.Exception
import java.util.ArrayList

class PostViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    enum class ApiStatus { LOADING, ERROR, DONE }

    private val _data = MutableLiveData<List<PostModel>>()
    val data: LiveData<List<PostModel>>
        get() = _data



    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getData()
    }
    private fun getData() {
        coroutineScope.launch {
            var getPropertiesDeferred = ApiService.MarsApi.retrofitService.getPosts()
            try {
                _status.value = ApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _data.value = listResult
                _status.value = ApiStatus.DONE
                Log.i("RESPONSE", _status.value.toString())
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _data.value = ArrayList()
            }
        }
    }

}