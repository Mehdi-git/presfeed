package dev.mehdi.bakhtiari.presfeed.ui.topheadlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.mehdi.bakhtiari.presfeed.BuildConfig
import dev.mehdi.bakhtiari.presfeed.data.model.Article
import dev.mehdi.bakhtiari.presfeed.data.model.NewsResponse
import dev.mehdi.bakhtiari.presfeed.data.model.Source
import dev.mehdi.bakhtiari.presfeed.data.remote.ApiResult
import dev.mehdi.bakhtiari.presfeed.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class TopHeadlineViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var mockObserver: Observer<List<Article>>

    private lateinit var viewModel: TopHeadlineViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = TopHeadlineViewModel(mockRepository)
        viewModel.headlineList.observeForever(mockObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        testCoroutineScope.cleanupTestCoroutines()
    }


    @Test
    fun `test getTopHeadline success`() = testCoroutineScope.runTest {
       // Given
         val newsResponse = NewsResponse("success", 10,
            listOf(Article("author",
                "title",
                "description",
                "url",
                "urlToImage",
                "publishedAt",
                "content",
                Source("id", "name")
            )))
        val apiResult = ApiResult.Success(newsResponse)
        `when`(mockRepository.getTopHeadline(BuildConfig.NEWS_SOURCE)).thenReturn(apiResult)

        // When
        viewModel.getTopHeadline(BuildConfig.NEWS_SOURCE)

        // Assert
        assertEquals(newsResponse.articles?.sortedBy { it.publishedAt }, viewModel.headlineList.value)
        assertEquals(newsResponse.articles?.firstOrNull()?.source?.name ?: BuildConfig.NEWS_SOURCE, viewModel.toolbarTitle.value)
        assertFalse(viewModel.loading.value!!)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `test getTopHeadline error`() = testCoroutineScope.runTest {
        // Arrange
        val source = BuildConfig.NEWS_SOURCE
        val errorMessage = "Error"
        val apiResult = ApiResult.Error(errorMessage)
        `when`(mockRepository.getTopHeadline(source)).thenReturn(apiResult)

        // Act
        viewModel.getTopHeadline(source)

        // Assert
        assertNull(viewModel.headlineList.value)
        assertNull(viewModel.toolbarTitle.value)
        assertFalse(viewModel.loading.value!!)
        assertEquals(errorMessage, viewModel.error.value)
    }

    @Test
    fun testHandleResponse_Success_EmptyArticles() = testCoroutineScope.runTest {

        // Given
        val list = emptyList<Article>()
        val response = ApiResult.Success(data = NewsResponse(status = "ok", totalResults = 0, articles = list))

        // When
        viewModel.handleResponse(response)

        // Assert
        assertEquals(false, viewModel.loading.value)
        assertEquals(BuildConfig.NEWS_SOURCE, viewModel.toolbarTitle.value)
        assertEquals(list, viewModel.headlineList.value)
        assertEquals(null, viewModel.error.value)
    }

    @Test
    fun testHandleResponse_Success() = testCoroutineScope.runTest {

        // Given
        val source = Source("sourceId","sourceName")
        val article = Article("author1", "title1", "description1", "url1",
            "urlToImage1", "01-Apr-2023", "content1", source)
        val article2 = Article("author2", "title2", "description2", "url2",
            "urlToImage2", "01-May-2023", "content2", source)
        val articles = listOf(article, article2)
        val response = ApiResult.Success(NewsResponse("ok", articles.size, articles))

        // When
        viewModel.handleResponse(response)

        // Assert
        assertEquals(false, viewModel.loading.value)
        assertEquals("sourceName", viewModel.toolbarTitle.value)
    }

    @Test
    fun testHandleResponse_Error() = testCoroutineScope.runTest {
        // Given
        val response = ApiResult.Error("Error message")

        // When
        viewModel.handleResponse(response)

        // Then
        assertEquals(false, viewModel.loading.value)
        assertEquals("Error message", viewModel.error.value)
        verifyNoMoreInteractions(mockObserver)
    }

}


