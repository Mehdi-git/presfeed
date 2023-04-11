package dev.mehdi.bakhtiari.presfeed.ui.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mehdi.bakhtiari.presfeed.BuildConfig
import dev.mehdi.bakhtiari.presfeed.data.model.Article
import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import dev.mehdi.bakhtiari.presfeed.data.remote.ApiResult
import dev.mehdi.bakhtiari.presfeed.data.repository.Repository
import dev.mehdi.bakhtiari.presfeed.utils.SingleEventLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
 private val repository: Repository,
 ) : ViewModel() {

    private val _headlinesList = MutableLiveData<List<Article>>()
    val headlineList: LiveData<List<Article>> = _headlinesList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = SingleEventLiveData<String>()
    val error: LiveData<String> = _error

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String> = _toolbarTitle

    init {
       getTopHeadline(BuildConfig.NEWS_SOURCE)
    }

    fun getTopHeadline(source: String) {
        _loading.value = true
        viewModelScope.launch {
            val result = repository.getTopHeadline(source)
            handleResponse(result)
        }
    }

    fun handleResponse(response: ApiResult<NewsResponse>) {
        _loading.value = false
        when (response) {
            is ApiResult.Success -> response.data.articles?.let { articles ->
                _toolbarTitle.value = articles.firstOrNull()?.source?.name ?: BuildConfig.NEWS_SOURCE
                _headlinesList.value = articles.sortedByDescending { it.publishedAt }
            }
            is ApiResult.Error -> response.message?.let {
                _error.value = it
            }
        }
    }
}
