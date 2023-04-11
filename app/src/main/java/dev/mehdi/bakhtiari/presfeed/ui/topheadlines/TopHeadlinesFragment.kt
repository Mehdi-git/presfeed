package dev.mehdi.bakhtiari.presfeed.ui.topheadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.mehdi.bakhtiari.presfeed.R
import dev.mehdi.bakhtiari.presfeed.data.model.Article
import dev.mehdi.bakhtiari.presfeed.data.model.toArticleUiModel
import dev.mehdi.bakhtiari.presfeed.databinding.FragmentHeadlinesBinding
import dev.mehdi.bakhtiari.presfeed.utils.toast
import dev.mehdi.bakhtiari.presfeed.utils.visibility
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TopHeadlinesFragment : Fragment() {

    private val viewModel: TopHeadlineViewModel by viewModels()
    private var _binding: FragmentHeadlinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var topHeadlinesAdapter: TopHeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadlinesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        topHeadlinesAdapter = TopHeadlinesAdapter {
            onItemClicked(it)
        }
        binding.recyclerView.adapter = topHeadlinesAdapter
        observeToHeadlines()
    }

    private fun observeToHeadlines() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loading.observe(viewLifecycleOwner) { loadingState ->
                binding.progressBar.visibility(loadingState)
            }
            viewModel.error.observe(viewLifecycleOwner) { error ->
                toast(error, Toast.LENGTH_LONG)
            }
            viewModel.headlineList.observe(viewLifecycleOwner) { articles ->
                topHeadlinesAdapter.submitList(articles)
            }
            viewModel.toolbarTitle.observe(viewLifecycleOwner) { title ->
                binding.toolbarTitle.text = title
            }
        }
    }

    private fun onItemClicked(article: Article) {
        val articleBundle = Bundle()
        articleBundle.putParcelable(HEADLINES_FRAGMENT_ARG, article.toArticleUiModel())
        val navController = findNavController()
        navController.navigate(R.id.action_headlines_Fragment_to_details_fragment, articleBundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val HEADLINES_FRAGMENT_ARG = "headlineFragmentArgs"
    }
}