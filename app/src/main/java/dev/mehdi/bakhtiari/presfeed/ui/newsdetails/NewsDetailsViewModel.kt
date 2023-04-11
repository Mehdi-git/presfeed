package dev.mehdi.bakhtiari.presfeed.ui.newsdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mehdi.bakhtiari.presfeed.data.model.ArticleUiModel
import dev.mehdi.bakhtiari.presfeed.utils.SingleEventLiveData
import javax.inject.Inject


@HiltViewModel
class NewsDetailsViewModel @Inject constructor() : ViewModel() {

    private val _articleDetails = MutableLiveData<ArticleUiModel>()
    val articleDetails: LiveData<ArticleUiModel> = _articleDetails

    private val _error = SingleEventLiveData<String>()
    val error: LiveData<String> = _error


    fun setArticleDetails(articleUiModel: ArticleUiModel?) {
        articleUiModel?.let {
            _articleDetails.value = it
        } ?: run {
            _error.value = "Some error occurred"
        }
    }
}